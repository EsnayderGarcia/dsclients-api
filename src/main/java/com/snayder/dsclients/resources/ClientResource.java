package com.snayder.dsclients.resources;

import com.snayder.dsclients.dtos.ClientDTO;
import com.snayder.dsclients.services.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import static org.springframework.data.domain.Sort.Direction.valueOf;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ApiOperation("Busca paginada de clientes")
    public ResponseEntity<Page<ClientDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "income") String sort
    ) {
       PageRequest pageRequest = PageRequest.of(page, size, valueOf(direction), sort);

        Page<ClientDTO> dtos = this.clientService.findAll(pageRequest);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{idClient}")
    @ApiOperation("Busca de um cliente pelo id")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long idClient) {
        ClientDTO dto = this.clientService.findById(idClient);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ApiOperation("Inserção de um cliente")
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
        dto = this.clientService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{idClient}")
    @ApiOperation("Atualização de um cliente pelo id")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO dto,
                                            @PathVariable Long idClient) {
        dto = this.clientService.update(idClient, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{idClient}")
    @ApiOperation("Deleção de um cliente pelo id")
    public ResponseEntity<Void> delete(@PathVariable Long idClient) {
        this.clientService.delete(idClient);
        return ResponseEntity.noContent().build();
    }

}
