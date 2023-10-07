package com.snayder.dsclients.dtos;

import com.snayder.dsclients.entities.Client;
import com.snayder.dsclients.entities.Emprego;


public class EmpregoRequest {
    private Long id;
    private String cargo;
    private String descricao;

    public EmpregoRequest(Long id, String cargo, String descricao) {
        this.id = id;
        this.cargo = cargo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Emprego toModel(Client client) {
        Emprego emprego = new Emprego(this);
        if(client.getId() != null)
            emprego.setId(id);

        emprego.setClient(client);

        return emprego;
    }
}
