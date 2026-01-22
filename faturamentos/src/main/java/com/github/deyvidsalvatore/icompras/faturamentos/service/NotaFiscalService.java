package com.github.deyvidsalvatore.icompras.faturamentos.service;

import com.github.deyvidsalvatore.icompras.faturamentos.model.Pedido;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotaFiscalService {

    @Value("classpath:reports/nota-fiscal.jrxml")
    private Resource notaFiscal;

    @Value("classpath:reports/logo.png")
    private Resource logo;

    public byte[] gerarNota(Pedido pedido) {
        try(InputStream inputStream = notaFiscal.getInputStream()) {
            var notaParams = notaParams(pedido);
            var dataSource = new JRBeanCollectionDataSource(pedido.itens());

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, notaParams, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> notaParams(Pedido pedido) throws IOException {
        Map<String, Object> notaParams = new HashMap<>();
        notaParams.put("NOME", pedido.cliente().nome());
        notaParams.put("CPF", pedido.cliente().cpf());
        notaParams.put("LOGRADOURO", pedido.cliente().logradouro());
        notaParams.put("BAIRRO", pedido.cliente().bairro());
        notaParams.put("NUMERO", pedido.cliente().numero());
        notaParams.put("EMAIL", pedido.cliente().email());
        notaParams.put("TELEFONE", pedido.cliente().telefone());

        notaParams.put("LOGO", logo.getFile().getAbsolutePath());

        notaParams.put("DATA_PEDIDO", pedido.data());
        notaParams.put("TOTAL_PEDIDO", pedido.total());

        return notaParams;

    }
}
