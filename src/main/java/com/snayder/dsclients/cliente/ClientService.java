package com.snayder.dsclients.cliente;

import com.snayder.dsclients.exceptions.ResourceNotFoundException;
import com.snayder.relatorio.cliente.ClienteRelatorio;
import com.snayder.relatorio.cliente.ClienteRelatorioDto;
import com.snayder.relatorio.cliente.InformacoesRelatorioClienteDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientResponse> findAll(PageRequest pageRequest) {
        Page<Client> clients = this.clientRepository.findAll(pageRequest);
        return clients.map(ClientResponse::new);
    }

    @Transactional(readOnly = true)
    public ClientResponse findById(Long idClient) {
        Client client = this.clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

        return new ClientResponse(client);
    }

    @Transactional
    public ClientResponse insert(ClientRequest clientRequest) {
        Client client = this.clientRepository.save(ClientConverter.convertToClient(clientRequest));
        return new ClientResponse(client);
    }

    @Transactional
    public ClientResponse update(Long idClient, ClientRequest clientRequest) {
        try {
            Client client = this.clientRepository.findById(idClient).get();
            client = ClientConverter.convertToClient(clientRequest);
            client = this.clientRepository.save(client);

            return new ClientResponse(client);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Cliente não encontrado para atualização!");
        }
    }

    @Transactional
    public void delete(Long idClient) {
        try {
            this.clientRepository.deleteById(idClient);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Cliente não encotrado para exclusão!");
        }
    }

    public byte[] generateReport() {
        List<ClienteRelatorioDto> clientesRelatorioList = clientRepository.findAll()
                .stream()
                .map(ClientConverter::convertToClienteRelatorioDto)
                .toList();

        return ClienteRelatorio.gerarRelatorioClientes(new InformacoesRelatorioClienteDto(clientesRelatorioList));
    }
}