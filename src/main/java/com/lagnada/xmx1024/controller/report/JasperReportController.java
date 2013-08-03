package com.lagnada.xmx1024.controller.report;

import de.svenjacobs.loremipsum.LoremIpsum;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class JasperReportController
{

    enum OutputType
    {
        pdf, xls
    }

    @Value("classpath:JasperDesign.jrxml")
    private Resource resource;

    @RequestMapping(value = "/jasper", method = GET)
    public String handleJasper()
    {
        return "jasper";
    }


    @RequestMapping(value = "/jasper", method = POST)
    public void handleJasperExport(@RequestParam(value = "type", defaultValue = "pdf") OutputType outputType, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        long start = System.currentTimeMillis();

        JasperDesign jasperDesign = JRXmlLoader.load(resource.getInputStream());
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ReportTitle", "Customer Report");
        parameters.put("Logo", "http://jasperreports.sourceforge.net/jasperreports.png");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(getCustomers()));
        JRExporter exporter;
        if (outputType == OutputType.xls)
        {
            exporter = new JRXlsExporter();
            response.setContentType("application/xls");
            response.setHeader("Content-Disposition", "attachment; filename=jasper.xls");
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        }
        else
        {
            exporter = new JRPdfExporter();
            response.setContentType("application/pdf");
            //response.setHeader("Content-Disposition", "attachment; filename=example.pdf");
        }
        response.setCharacterEncoding("UTF-8");
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        exporter.exportReport();
        System.err.println("Filling time : " + (System.currentTimeMillis() - start));
    }

    private List<Customer> getCustomers()
    {
        List<Customer> customers = new ArrayList<Customer>();
        Customer c;
        Random random = new Random(1000);
        LoremIpsum loremIpsum = new LoremIpsum();
        for (int i = 1; i < 100; i++)
        {
            c = new Customer(Long.valueOf(i));
            c.setFirstName("John");
            c.setLastName("Doe");
            c.setBalance(random.nextDouble());
            c.setPhone("425-555-1212");
            c.setNotes(loremIpsum.getWords(10, random.nextInt(10)));
            customers.add(c);
        }

        return customers;
    }


}
