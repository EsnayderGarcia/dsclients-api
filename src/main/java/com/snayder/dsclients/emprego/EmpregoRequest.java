package com.snayder.dsclients.emprego;

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
}
