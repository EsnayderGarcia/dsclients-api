package com.snayder.dsclients.cliente;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface ClientSwagger {
    @ApiOperation("Busca paginada de clientes")
    ResponseEntity<Page<ClientResponse>> findAll(
        @RequestParam(defaultValue = "0") @ApiParam(value = "Número da Página") int page,
        @RequestParam(defaultValue = "5") @ApiParam(value = "Clientes por Página") int size
    );

    @ApiOperation("Busca um cliente por id")
    ResponseEntity<ClientResponse> findById(@ApiParam(value = "Id do Cliente", example = "1") Long idClient);

    @ApiOperation("Insere um cliente")
    ResponseEntity<ClientResponse> insert(ClientRequest dto);

    @ApiOperation("Atualiza um cliente por id")
    ResponseEntity<ClientResponse> update(
        ClientRequest dto,
        @ApiParam(value = "Id do Cliente", example = "1") Long idClient
    );

    @ApiOperation("Remove um cliente por id")
    ResponseEntity<Void> delete(@ApiParam(value = "Id do Cliente", example = "1") Long idClient);

//    @ApiOperation("Recupera um array de bytes para geração do relatório de clientes em PDF")
//    ResponseEntity<byte[]> generateClientsReport();
//
//    @ApiOperation("Recupera um array de bytes para geração do relatório de detalhes do cliente em PDF")
//    ResponseEntity<byte[]> generateDetailsClientsReport(
//        @ApiParam(value = "Id do Cliente", example = "1") Long clientId
//    );
}
