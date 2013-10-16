package com.lagnada.xmx1024.controller.w8;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.String.format;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/w8")
public class W8Controller
{
    @Value("classpath:fw8ben.pdf")
    private Resource blankW8Pdf;

    @ModelAttribute("w8Model")
    private W8Model w8Model()
    {
        return new W8Model();
    }

    @RequestMapping(method = GET)
    public String handleView()
    {
        return "w8";
    }

    @RequestMapping(method = POST)
    public String handlePost(@Valid W8Model w8Model, BindingResult bindingResult, HttpServletResponse response) throws Exception
    {
        if (bindingResult.hasErrors())
        {
            return "w8";
        }

        PDDocument pdf = loadPdfDoc();
        try
        {
            PDDocumentCatalog docCatalog = pdf.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            PDField nameOfIndividualOrOrganizationField = acroForm.getField("f1-1.f1-1");
            nameOfIndividualOrOrganizationField.setValue(w8Model.getPartOne().getBeneficialOwnerName());

            PDField incorporationCountryField = acroForm.getField("f1-2.f1-2");
            incorporationCountryField.setValue(w8Model.getPartOne().getIncorporationCountry());

            PDField streetField = acroForm.getField("f1-3.f1-3");
            streetField.setValue(w8Model.getPartOne().getPermanentResidenceAddress().getStreetAddress());

            PDField cityField = acroForm.getField("f1-4.f1-4");
            cityField.setValue("Hilsinki");

            PDField countryField = acroForm.getField("f1-5.f1-5");
            countryField.setValue("Finland");

            PDField aCheckbox = acroForm.getField("c1-3.c1-3");
            aCheckbox.setValue("Yes");

            String test = "that";
            System.out.println(test);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", format("attachment; filename=%s.pdf", "fw8ben_filled"));
            pdf.save(response.getOutputStream());
        }
        finally
        {
            closePdfQuietly(pdf);
        }
        return null;
    }

    private PDDocument loadPdfDoc() throws IOException
    {
        InputStream is = null;
        PDDocument pdfDoc;
        try
        {
            is = blankW8Pdf.getInputStream();
            pdfDoc = PDDocument.load(is);
        }
        finally
        {
            closeQuietly(is);
        }
        return pdfDoc;
    }

    private void closePdfQuietly(PDDocument pdf) throws IOException
    {
        if (pdf != null)
        {
            pdf.close();
        }
    }

}
