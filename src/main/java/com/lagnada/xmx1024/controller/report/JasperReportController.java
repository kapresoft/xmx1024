package com.lagnada.xmx1024.controller.report;

import de.svenjacobs.loremipsum.LoremIpsum;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class JasperReportController {

    //@Value("classpath:JasperDesign.jrxml")
    //private Resource resource;

    @RequestMapping(value = "/reports", method = GET)
    public String handleJasper() {
        return "reports";
    }

    @RequestMapping(value = "/reports/view", method = {GET, POST})
    public void handleJasperExport(@RequestParam(value = "type", defaultValue = "pdf") OutputType outputType,
                                   @ModelAttribute("reportsModel") ReportsModel reportsModel,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        ReportsBuilder.newBuilder()
                .reportsModel(reportsModel)
                .rowData(getCustomers())
                .designName("JasperDesign.jrxml")
                .outputType(outputType)
                .response(response)
                .formatFactory(CustomFormatFactory.fromCurrencyCode("EUR"))
                .build();

    }

//    @RequestMapping(value = "/reports/unicode", method = POST)
//    public void handleJasperExportUnicode(@RequestParam(value = "type", defaultValue = "pdf") OutputType outputType,
//                                          HttpServletRequest request, HttpServletResponse response) throws Exception {
//        long start = System.currentTimeMillis();
//
//        JasperReport jasperReport = compileJasperReport("UnicodeReport.jrxml");
//
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("ChineseText", "敏捷的棕色狐狸跃过那只懒狗。");
//
//        ///parameters.put("Logo", "http://jasperreports.sourceforge.net/jasperreports.png");
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(getCustomers()));
//        JRExporter exporter;
//        if (outputType == OutputType.xls) {
//            exporter = new JRXlsExporter();
//            response.setContentType("application/xls");
//            response.setHeader("Content-Disposition", "attachment; filename=jasper.xls");
//            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//        } else {
//            exporter = new JRPdfExporter();
//            response.setContentType("application/pdf");
//            //response.setHeader("Content-Disposition", "attachment; filename=example.pdf");
//        }
//        response.setCharacterEncoding("UTF-8");
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
//        exporter.exportReport();
//        System.err.println("Filling time : " + (System.currentTimeMillis() - start));
//    }

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
            c.setNotes(StringUtils.capitalize(loremIpsum.getWords(4 + random.nextInt(10), random.nextInt(10))));
            customers.add(c);
        }

        return customers;
    }


}
