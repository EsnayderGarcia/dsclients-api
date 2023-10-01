package com.snayder.dsclients.dtos;

import com.snayder.dsclients.entities.Emprego;

public class EmpregoResponse {

    private Long id;
    private String cargo;
    private String descricao;

    public EmpregoResponse(Emprego emprego) {
        id = emprego.getId();
        cargo = emprego.getCargo();
        descricao = emprego.getDescricao();
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
}
