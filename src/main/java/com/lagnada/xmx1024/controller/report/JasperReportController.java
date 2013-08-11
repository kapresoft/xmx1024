package com.lagnada.xmx1024.controller.report;

import de.svenjacobs.loremipsum.LoremIpsum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
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
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import static org.springframework.util.StringUtils.countOccurrencesOf;
import static org.springframework.util.StringUtils.hasText;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class JasperReportController {

    enum OutputType {
        pdf, xls
    }

    //@Value("classpath:JasperDesign.jrxml")
    //private Resource resource;

    @RequestMapping(value = "/reports", method = GET)
    public String handleJasper() {
        return "reports";
    }

    private JasperDesign loadJasperDesign(String designName) {
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/jasper-designs/" + designName)
                .build()
                .toUri().toASCIIString();
        InputStream inputStream = null;
        JasperDesign jasperDesign = null;
        Resource resource = null;
        try {
            resource = new UrlResource(uri);
            inputStream = resource.getInputStream();
            jasperDesign = JRXmlLoader.load(resource.getInputStream());
        } catch (IOException e) {
            IOUtils.closeQuietly(inputStream);
        } catch (JRException e) {
            throw new IllegalStateException(e);
        }
        return jasperDesign;
    }

    private JasperReport compileJasperReport(String designName) {
        try {
            return JasperCompileManager.compileReport(loadJasperDesign(designName));
        } catch (JRException e) {
            throw new IllegalStateException(e);
        }
    }


    @RequestMapping(value = "/reports/view", method = {GET, POST})
    public void handleJasperExport(@RequestParam(value = "type", defaultValue = "pdf") OutputType outputType, HttpServletRequest request, HttpServletResponse response) throws Exception {
        long start = System.currentTimeMillis();

        JasperReport jasperReport = compileJasperReport("JasperDesign.jrxml");

        Map<String, Object> parameters = new HashMap<String, Object>();

        Locale locale = LocaleContextHolder.getLocale();
        parameters.put(JRParameter.REPORT_LOCALE, locale);
        if (!hasText(locale.getCountry())) {
            locale = Locale.US;
        }

        // DateFormat.getDateInstance(DateFormat.MEDIUM, $P{REPORT_LOCALE}).format($F{DATE})
        // new SimpleDateFormat("MM/dd/yy").format($F{DATE})
        //Currency currency = Currency.getInstance(locale);
        //String currencyCode = currency.getCurrencyCode();
        //String currencySymbol = currency.getSymbol(locale);
        //System.out.printf("Locale:[%s] Currency code:[%s] symbol[%s]",
        //        locale, currencyCode, currencySymbol);
        //NumberFormat currencyFormat = NumberFormat.getInstance(locale);
        //NumberFormat currencyFormat = new DecimalFormat("#,##0.00");
        //currencyFormat.setCurrency(currency);
        //parameters.put("currencyFormat", currencyFormat);
        //parameters.put("currencySymbol", currencySymbol);

        parameters.put("dateFormat", transformShortDatePattern(locale));

        // parameters.put("ReportTitle", "Pohjoisen kesän yöttömät yöt,");
        parameters.put("ReportTitle", "敏捷的棕色狐狸跃过那只懒狗。");
        parameters.put("Logo", "http://jasperreports.sourceforge.net/jasperreports.png");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(getCustomers()));
        JRExporter exporter;
        if (outputType == OutputType.xls) {
            exporter = new JRXlsExporter();
            response.setContentType("application/xls");
            response.setHeader("Content-Disposition", "attachment; filename=jasper.xls");
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        } else {
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

    /**
     * Transform DateFormat.SHORT from "M/d/yy" to "MM/dd/yy"
     */
    private SimpleDateFormat transformShortDatePattern(Locale locale) {
        SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale);
        String datePattern = dateFormat.toPattern();
        if (countOccurrencesOf(datePattern, "M") == 1) {
            datePattern = datePattern.replace("M", "MM");
        }
        if (countOccurrencesOf(datePattern, "d") == 1) {
            datePattern = datePattern.replace("d", "dd");
        }
        dateFormat.applyPattern(datePattern);
        return dateFormat;
    }

    @RequestMapping(value = "/reports/unicode", method = GET)
    public void handleJasperExportUnicode(@RequestParam(value = "type", defaultValue = "pdf") OutputType outputType, HttpServletRequest request, HttpServletResponse response) throws Exception {
        long start = System.currentTimeMillis();

        JasperReport jasperReport = compileJasperReport("UnicodeReport.jrxml");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ChineseText", "敏捷的棕色狐狸跃过那只懒狗。");

        ///parameters.put("Logo", "http://jasperreports.sourceforge.net/jasperreports.png");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(getCustomers()));
        JRExporter exporter;
        if (outputType == OutputType.xls) {
            exporter = new JRXlsExporter();
            response.setContentType("application/xls");
            response.setHeader("Content-Disposition", "attachment; filename=jasper.xls");
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        } else {
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

    private List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        Customer c;
        Random random = new Random(1000);
        LoremIpsum loremIpsum = new LoremIpsum();
        for (int i = 1; i < 100; i++) {
            c = new Customer(Long.valueOf(i));
            c.setFirstName("John约翰");
            c.setLastName("Doe李四");
            c.setBalance(Integer.valueOf(1 + random.nextInt(100)).doubleValue() + random.nextDouble());
            Date date = DateTime.now()
                    .withYear(2012 + random.nextInt(5))
                    .withDayOfYear(1 + random.nextInt(364))
                    .toDate();
            c.setDate(date);
            c.setPhone("425-555-1212");
            c.setNotes(loremIpsum.getWords(10, random.nextInt(10)));
            customers.add(c);
        }

        return customers;
    }


}
