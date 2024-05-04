package com.snayder.dsclients.cliente;

import com.snayder.dsclients.emprego.EmpregoConverter;
import com.snayder.dsclients.emprego.EmpregoResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientResponse {
    private Long id;
    private String name;
    private String cpfCnpj;
    private LocalDate birthDate;
    private Integer children;
    private final List<EmpregoResponse> empregos = new ArrayList<>();

    public ClientResponse(Client client) {
        id = client.getId();
        name = client.getName();
        cpfCnpj = client.getCpfCnpj();
        birthDate = client.getBirthDate();
        children = client.getChildren();
        client.getEmpregos().forEach(e -> empregos.add(EmpregoConverter.toEmpregoResponse(e)));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public List<EmpregoResponse> getEmpregos() {
        return empregos;
    }
}
