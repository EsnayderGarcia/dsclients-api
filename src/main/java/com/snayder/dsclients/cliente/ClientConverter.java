package com.snayder.dsclients.cliente;

import com.snayder.dsclients.emprego.EmpregoConverter;

public class ClientConverter {
    public static Client convertToClient(ClientRequest clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setCpfCnpj(clientRequest.getDocumento());
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
        client.setCpfCnpj(clientRequest.getDocumento());
        client.setBirthDate(clientRequest.getBirthDate());
        client.setChildren(clientRequest.getChildren());

        client.getEmpregos().clear();

        if (!clientRequest.getEmpregos().isEmpty()) {
            clientRequest.getEmpregos()
                    .forEach(empregoRequest -> client.getEmpregos().add(EmpregoConverter.toEmprego(empregoRequest, client)));
        }
    }
}

