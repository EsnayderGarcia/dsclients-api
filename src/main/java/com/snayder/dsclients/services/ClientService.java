package com.snayder.dsclients.services;

import com.snayder.dsclients.dtos.ClientDTO;
import com.snayder.dsclients.entities.Client;
import com.snayder.dsclients.repositories.ClientRepository;
import com.snayder.dsclients.services.exceptions.DatabaseViolationException;
import com.snayder.dsclients.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(PageRequest pageRequest) {
        Page<Client> clients = this.clientRepository.findAll(pageRequest);
        return clients.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long idClient) {
        Client client = this.clientRepository.findById(idClient)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        try {
            Client client = this.clientRepository.save(new Client(dto));
            return new ClientDTO(client);
        }
        catch (DataIntegrityViolationException ex) {
            throw new DatabaseViolationException("O CPF informado já está vinculado à outro cliente");
        }
    }

    @Transactional
    public ClientDTO update(Long idClient, ClientDTO dto) {
        try {
            Client client = this.clientRepository.getById(idClient);

            client.convertToClient(dto);
            client = this.clientRepository.saveAndFlush(client);

            return new ClientDTO(client);
        }
        catch (DataIntegrityViolationException ex) {
            throw new DatabaseViolationException("O CPF informado já está vinculado à outro cliente");
        }
        catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Cliente não encontrado para atualização!");
        }
    }

    public void delete(Long idClient) {
        try {
            this.clientRepository.deleteById(idClient);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Cliente não encotrado para exclusão!");
        }
    }

}
