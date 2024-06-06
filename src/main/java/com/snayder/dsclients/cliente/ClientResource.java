package com.snayder.dsclients.cliente;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<ClientResponse> insert(@RequestBody @Valid ClientRequest dto) {
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
    public ResponseEntity<ClientResponse> update(@RequestBody @Valid ClientRequest dto, @PathVariable Long idClient) {
        return ResponseEntity.ok(clientService.update(idClient, dto));
    }
}
