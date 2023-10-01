package com.snayder.dsclients.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ReportService {
    private static final String CONTENT_TYPE_PDF = "application/pdf";
    private static final String CONTENT_TYPE_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public void generateReport(ServletContext context, HttpServletResponse resp, String reportName,
                               Map<String, Object> params,
                               JRBeanCollectionDataSource data,
                               boolean toExcel) throws JRException, IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        StringBuilder reportPath = new StringBuilder();
        reportPath.append(context.getRealPath("reports"))
                .append(File.separator)
                .append(reportName)
                .append(".jasper");

        JasperPrint print = JasperFillManager.fillReport(reportPath.toString(), params, data);
        JRExporter exporter = toExcel ? new JRXlsExporter() : new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        exporter.exportReport();

        resp.addHeader("Content-Type", toExcel ? CONTENT_TYPE_EXCEL : CONTENT_TYPE_PDF);
        resp.addHeader("content-disposition", "attachment; filename="+reportName+(toExcel ? ".xls" : ".pdf"));
        resp.setContentLength(baos.size());
        resp.getOutputStream().write(baos.toByteArray());
        resp.getOutputStream().flush();

        baos.flush();
        baos.close();
    }
}
