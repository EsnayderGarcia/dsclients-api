package com.snayder.dsclients.dtos;

import com.snayder.dsclients.entities.Emprego;

public class EmpregoResponse {

    private Long id;
    private String cargo;
    private String descricao;
    private boolean ativo;

    public EmpregoResponse(Emprego emprego) {
        id = emprego.getId();
        cargo = emprego.getCargo();
        descricao = emprego.getDescricao();
        ativo = emprego.isAtivo();
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

    public boolean isAtivo() {
        return ativo;
    }
}
