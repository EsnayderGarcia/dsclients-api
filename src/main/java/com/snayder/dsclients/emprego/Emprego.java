package com.snayder.dsclients.emprego;

import com.snayder.dsclients.cliente.Client;

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
        id = empregoRequest.getId();
        cargo = empregoRequest.getCargo();
        descricao = empregoRequest.getDescricao();
    }

    public Emprego(EmpregoRequest empregoRequest, Client client) {
        id = empregoRequest.getId();
        cargo = empregoRequest.getCargo();
        descricao = empregoRequest.getDescricao();
        this.client = client;
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
