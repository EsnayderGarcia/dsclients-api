package com.snayder.dsclients.dtos;

import com.snayder.dsclients.entities.Client;
import com.snayder.dsclients.entities.Emprego;


public class EmpregoRequest {
    private Long id;
    private String cargo;
    private String descricao;
    private boolean ativo;

    public EmpregoRequest(Long id, String cargo, String descricao, boolean situacao) {
        this.id = id;
        this.cargo = cargo;
        this.descricao = descricao;
        this.ativo = situacao;
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

    public boolean getAtivo() {
        return ativo;
    }

    public Emprego toModel(Client client) {
        Emprego emprego = new Emprego(this);
        emprego.setClient(client);

        if (id != null && !ativo)
            emprego.setAtivo(false);

        return emprego;
    }
}
