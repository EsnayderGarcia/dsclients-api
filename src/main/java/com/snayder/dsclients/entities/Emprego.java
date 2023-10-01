package com.snayder.dsclients.entities;

import com.snayder.dsclients.dtos.EmpregoRequest;

import javax.persistence.*;

@Entity
@Table(name = "tb_empregos")
public class Emprego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cargo;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Emprego() {
    }

    public Emprego(EmpregoRequest empregoRequest) {
        cargo = empregoRequest.getCargo();
        descricao = empregoRequest.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
