package com.snayder.dsclients.relatorio;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class RelatorioUtil {
    static byte[] gerarRelatorio(InputStream stream, Map<String, Object> params, List<?> dataSource) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(stream);
            JasperPrint print = JasperFillManager.fillReport(
                jasperReport,
                params,
                new JRBeanCollectionDataSource(dataSource)
            );

            return JasperExportManager.exportReportToPdf(print);
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}
