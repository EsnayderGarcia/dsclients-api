package com.snayder.dsclients.services;

import com.snayder.dsclients.dtos.ClientRequest;
import com.snayder.dsclients.dtos.ClientResponse;
import com.snayder.dsclients.entities.Client;
import com.snayder.dsclients.repositories.ClientRepository;
import com.snayder.dsclients.services.exceptions.ResourceNotFoundException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ReportService reportService;

    public ClientService(ClientRepository clientRepository, ReportService reportService) {
        this.clientRepository = clientRepository;
        this.reportService = reportService;
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
    public ClientResponse insert(ClientRequest dto) {
        Client client = this.clientRepository.save(dto.toModel());
        return new ClientResponse(client);
    }

    @Transactional
    public ClientResponse update(Long idClient, ClientRequest clientRequest) {
        try {
            Client client = this.clientRepository.getById(idClient);
            client.atualizar(clientRequest);
            client = this.clientRepository.save(client);

            return new ClientResponse(client);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Cliente não encontrado para atualização!");
        }
    }

    public void delete(Long idClient) {
        try {
            this.clientRepository.deleteById(idClient);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Cliente não encotrado para exclusão!");
        }
    }

    public void generateClientsReport(ServletContext context, HttpServletResponse resp, boolean toExcel) throws Exception {
        Map<String, Object> params = new HashMap<>();

        reportService.generateReport(
                context,
                resp,
                "rel_clients",
                params,
                new JRBeanCollectionDataSource(clientRepository.findAll()),
                toExcel
        );
    }
}
