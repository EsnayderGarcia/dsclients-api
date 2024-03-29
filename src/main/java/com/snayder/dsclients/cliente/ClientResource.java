package com.snayder.dsclients.cliente;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientResource implements ClientSwagger {
    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponse>> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(clientService.findAll(pageRequest));
    }

    @GetMapping("/{idClient}")
    @ApiOperation("Busca de um cliente pelo id")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long idClient) {
        return ResponseEntity.ok(clientService.findById(idClient));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> insert(@RequestBody ClientRequest dto) {
        ClientResponse clientResponse = this.clientService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).body(clientResponse);
    }

    @DeleteMapping("/{idClient}")
    public ResponseEntity<Void> delete(@PathVariable Long idClient) {
        clientService.delete(idClient);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<ClientResponse> update(@RequestBody ClientRequest dto, @PathVariable Long idClient) {
        return ResponseEntity.ok(clientService.update(idClient, dto));
    }

    @GetMapping("relatorio-clientes")
    public ResponseEntity<byte[]> generateClientsReport() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(clientService.generateReport(), headers, HttpStatus.OK);
    }

    @GetMapping("relatorio-detalhes-cliente/{clientId}")
    public ResponseEntity<byte[]> generateDetailsClientsReport(@PathVariable Long clientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(clientService.generateDetailsClientReport(clientId), headers, HttpStatus.OK);
    }
}
