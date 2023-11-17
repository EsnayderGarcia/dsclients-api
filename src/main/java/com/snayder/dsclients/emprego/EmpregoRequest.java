package com.snayder.dsclients.emprego;

import java.math.BigDecimal;

public class EmpregoRequest {
    private Long id;
    private String cargo;
    private String descricao;
    private BigDecimal salario;

    public EmpregoRequest(Long id, String cargo, String descricao, BigDecimal salario) {
        this.id = id;
        this.cargo = cargo;
        this.descricao = descricao;
        this.salario = salario;
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

    public BigDecimal getSalario() {
        return salario;
    }
}
