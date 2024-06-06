package com.snayder.dsclients.relatorio;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("relatorios")
public class RelatorioResource {
    private final RelatorioService relatorioService;

    public RelatorioResource(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("clientes")
    public ResponseEntity<byte[]> generateClientsReport() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(relatorioService.gerarPdfRelatorioClientes(), headers, HttpStatus.OK);
    }

    @GetMapping("clientes/{clientId}")
    public ResponseEntity<byte[]> generateDetailsClientsReport(@PathVariable Long clientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(relatorioService.gerarPdfRelatorioDetalhesCliente(clientId), headers, HttpStatus.OK);
    }
}
