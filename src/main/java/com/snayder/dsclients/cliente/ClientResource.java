package com.snayder.dsclients.cliente;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.data.domain.Sort.Direction.valueOf;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientResource {
    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @ApiOperation("Busca paginada de clientes")
    public ResponseEntity<Page<ClientResponse>> findAll(
	        @RequestParam(defaultValue = "0")  @ApiParam(value = "Número da Página") int page,
	        @RequestParam(defaultValue = "10") @ApiParam(value = "Clientes por Página") int size,
	        @RequestParam(defaultValue = "DESC") @ApiParam(value = "Tipo da Ordenação") String direction,
	        @RequestParam(defaultValue = "birthDate") @ApiParam(value = "Informe por qual dado os clientes serão ordenados") String sort) {
       PageRequest pageRequest = PageRequest.of(page, size, valueOf(direction), sort);
       Page<ClientResponse> dtos = this.clientService.findAll(pageRequest);
        
       return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{idClient}")
    @ApiOperation("Busca de um cliente pelo id")
    public ResponseEntity<ClientResponse> findById(
    		@PathVariable @ApiParam(value = "Id do Cliente", example = "1") Long idClient) {
        ClientResponse dto = this.clientService.findById(idClient);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ApiOperation("Inserção de um cliente")
    public ResponseEntity<ClientResponse> insert(@RequestBody ClientRequest dto) {
        ClientResponse clientResponse = this.clientService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).body(clientResponse);
    }

    @PutMapping("/{idClient}")
    @ApiOperation("Atualização de um cliente pelo id")
    public ResponseEntity<ClientResponse> update(
	    	@RequestBody ClientRequest dto,
	    	@PathVariable @ApiParam(value = "Id do Cliente", example = "1") Long idClient) {
        ClientResponse clientResponse = this.clientService.update(idClient, dto);
        return ResponseEntity.ok(clientResponse);
    }

    @DeleteMapping("/{idClient}")
    @ApiOperation("Deleção de um cliente pelo id")
    public ResponseEntity<Void> delete(
    		@PathVariable @ApiParam(value = "Id do Cliente", example = "1") Long idClient) {
        this.clientService.delete(idClient);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("relatorio-clientes")
    public ResponseEntity<byte[]> generateClientsReport() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(clientService.generateReport(), headers, HttpStatus.OK);
    }
}
