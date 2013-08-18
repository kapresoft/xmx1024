package com.lagnada.xmx1024.controller.report;

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
import net.sf.jasperreports.engine.util.FormatFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.io.IOUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.google.common.base.Optional.fromNullable;
import static org.springframework.util.StringUtils.hasText;

public class ReportsBuilder {

    private ReportsModel reportsModel;
    private String designName;
    private OutputType outputType;
    private HttpServletResponse response;
    private List<?> rowData;
    private FormatFactory formatFactory;
    private Locale locale;

    private ReportsBuilder() {
    }

    public static ReportsBuilder newBuilder() {
        return new ReportsBuilder()
                .locale(LocaleContextHolder.getLocale());
    }

    public ReportsBuilder reportsModel(ReportsModel reportsModel) {
        this.reportsModel = reportsModel;
        return this;
    }

    public ReportsBuilder designName(String designName) {
        this.designName = designName;
        return this;
    }

    public ReportsBuilder formatFactory(FormatFactory formatFactory) {
        this.formatFactory = formatFactory;
        return this;
    }

    public ReportsBuilder outputType(OutputType outputType) {
        this.outputType = outputType;
        return this;
    }

    public ReportsBuilder response(HttpServletResponse response) {
        this.response = response;
        return this;
    }

    public ReportsBuilder rowData(List<?> rowData) {
        this.rowData = rowData;
        return this;
    }

    public ReportsBuilder locale(Locale locale) {
        if (!hasText(locale.getCountry())) {
            locale = Locale.US;
            LocaleContextHolder.setLocale(locale);
        }
        this.locale = locale;
        return this;
    }

    public void build() throws Exception {
        long start = System.currentTimeMillis();
        JasperReport jasperReport = compileJasperReport(designName);

        Map<String, Object> parameters = new HashMap<String, Object>();

        String baseTemplateUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .pathSegment("jasper-designs")
                .build()
                .toUri()
                .toASCIIString();
        parameters.put("BaseTemplateLocation", baseTemplateUri);

        parameters.put(JRParameter.REPORT_LOCALE, locale);
        parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, reportsModel.toPropertyResourceBundle());

        if (fromNullable(formatFactory).isPresent()) {
            parameters.put(JRParameter.REPORT_FORMAT_FACTORY, formatFactory);
        }

        parameters.put("Logo", "http://jasperreports.sourceforge.net/jasperreports.png");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(rowData));
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
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        exporter.exportReport();

        System.err.printf("Generated in: %s ms%n", System.currentTimeMillis() - start);
    }

    private JasperReport compileJasperReport(String designName) {
        try {
            return JasperCompileManager.compileReport(loadJasperDesign(designName));
        } catch (JRException e) {
            throw new IllegalStateException(e);
        }
    }

    private JasperDesign loadJasperDesign(String designName) {
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/jasper-designs/" + designName)
                .build()
                .toUri().toASCIIString();
        InputStream inputStream = null;
        JasperDesign jasperDesign = null;
        Resource resource;
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

}
