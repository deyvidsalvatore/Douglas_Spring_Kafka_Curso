package com.github.deyvidsalvatore.icompras.faturamentos.subscriber;

import com.github.deyvidsalvatore.icompras.faturamentos.bucket.BucketFile;
import com.github.deyvidsalvatore.icompras.faturamentos.bucket.BucketService;
import com.github.deyvidsalvatore.icompras.faturamentos.model.Pedido;
import com.github.deyvidsalvatore.icompras.faturamentos.service.NotaFiscalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class GeradorNotaFiscalService {

    private final NotaFiscalService notaFiscalService;
    private final BucketService bucketService;

    public void gerar(Pedido pedido) {
        log.info("Gerada nota fiscal para o pedido {}", pedido.codigo());
        try {
            byte[] byteArray = notaFiscalService.gerarNota(pedido);
            String nomeArquivo = String.format("notafiscal_pedido_%d.pdf", pedido.codigo());
            var file = new BucketFile(nomeArquivo, new ByteArrayInputStream(byteArray), MediaType.APPLICATION_PDF, byteArray.length);
            this.bucketService.upload(file);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }


    }
}
