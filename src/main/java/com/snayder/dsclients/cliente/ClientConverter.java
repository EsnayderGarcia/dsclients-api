package com.snayder.dsclients.cliente;

import com.snayder.dsclients.emprego.Emprego;
import com.snayder.relatorio.cliente.ClienteRelatorioDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClientConverter {
    public static Client convertToClient(ClientRequest clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setCpf(clientRequest.getCpf());
        client.setBirthDate(clientRequest.getBirthDate());
        client.setChildren(clientRequest.getChildren());

        if (!clientRequest.getEmpregos().isEmpty()) {
            clientRequest.getEmpregos().forEach(empregoRequest -> client.getEmpregos().add(new Emprego(empregoRequest, client)));
        }

        return client;
    }

    public static void converterToClientUpdate(Client client, ClientRequest clientRequest) {
        client.setName(clientRequest.getName());
        client.setCpf(clientRequest.getCpf());
        client.setBirthDate(clientRequest.getBirthDate());
        client.setChildren(clientRequest.getChildren());

        client.getEmpregos().clear();

        if (!clientRequest.getEmpregos().isEmpty()) {
            clientRequest.getEmpregos().forEach(empregoRequest -> client.getEmpregos().add(new Emprego(empregoRequest, client)));
        }
    }

    public static ClienteRelatorioDto convertToClienteRelatorioDto(Client client) {
        ClienteRelatorioDto clienteRelatorioDto = new ClienteRelatorioDto();

        clienteRelatorioDto.setNome(client.getName());
        clienteRelatorioDto.setCpf(client.getCpf());

        try {
            clienteRelatorioDto.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(client.getBirthDate().toString()));
        }
        catch (ParseException e) {
            throw new RuntimeException("Não foi possível converter data: " + e);
        }

        return clienteRelatorioDto;
    }
}
