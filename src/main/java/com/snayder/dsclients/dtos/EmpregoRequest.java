package com.snayder.dsclients.dtos;

import com.snayder.dsclients.entities.Client;
import com.snayder.dsclients.entities.Emprego;

public class EmpregoRequest {
    private String cargo;
    private String descricao;

    public EmpregoRequest(String cargo, String descricao) {
        this.cargo = cargo;
        this.descricao = descricao;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Emprego toModel(Client client) {
        Emprego emprego = new Emprego(this);
        emprego.setClient(client);

        return emprego;
    }
}
