package com.snayder.dsclients.cliente;

import com.snayder.dsclients.emprego.EmpregoConverter;
import com.snayder.dsclients.util.Converter;
import com.snayder.relatorio.cliente.ClienteRelatorioDto;
import com.snayder.relatorio.cliente.InformacoesRelatorioDetalhesCliente;
import com.snayder.relatorio.emprego.EmpregoRelatorioDto;

import java.util.List;

public class ClientConverter {
    public static Client convertToClient(ClientRequest clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setCpf(clientRequest.getCpf());
        client.setBirthDate(clientRequest.getBirthDate());
        client.setChildren(clientRequest.getChildren());

        if (!clientRequest.getEmpregos().isEmpty()) {
            clientRequest.getEmpregos()
                    .forEach(empregoRequest -> client.getEmpregos().add(EmpregoConverter.toEmprego(empregoRequest, client)));
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
            clientRequest.getEmpregos()
                    .forEach(empregoRequest -> client.getEmpregos().add(EmpregoConverter.toEmprego(empregoRequest, client)));
        }
    }

    public static ClienteRelatorioDto convertToClienteRelatorioDto(Client client) {
        ClienteRelatorioDto clienteRelatorioDto = new ClienteRelatorioDto();

        clienteRelatorioDto.setNome(client.getName());
        clienteRelatorioDto.setCpf(client.getCpf());
        clienteRelatorioDto.setDataNascimento(Converter.localdateToString(client.getBirthDate()));

        return clienteRelatorioDto;
    }

    public static InformacoesRelatorioDetalhesCliente convertToClienteRelatorioDetalheDto(Client client) {
        ClienteRelatorioDto clienteRelatorioDto = convertToClienteRelatorioDto(client);

        List<EmpregoRelatorioDto> empregos = client.getEmpregos().stream().map(emprego -> {
            EmpregoRelatorioDto empregoRelatorioDto = new EmpregoRelatorioDto();
            empregoRelatorioDto.setCargo(emprego.getCargo());
            empregoRelatorioDto.setDescricao(emprego.getDescricao());
            empregoRelatorioDto.setSalario(emprego.getSalario());

            return empregoRelatorioDto;
        }).toList();

        return new InformacoesRelatorioDetalhesCliente(clienteRelatorioDto, empregos);
    }
}

