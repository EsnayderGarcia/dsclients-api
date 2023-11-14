package com.snayder.dsclients.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.snayder.dsclients.emprego.Emprego;
import com.snayder.dsclients.emprego.EmpregoRequest;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_clients")
@Where(clause = "situacao = 'A'")
@SQLDelete(sql = "update tb_clients set situacao = 'I' where id = ?")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String cpf;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	private Integer children;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;

	private String situacao = "A";

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Emprego> empregos = new ArrayList<>();

	public Client() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	public Instant getCreated_at() {
		return createdAt;
	}

	public Instant getUpdate_at() {
		return updatedAt;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<Emprego> getEmpregos() {
		return empregos;
	}

	@PrePersist
	private void prePersist() {
		createdAt = Instant.now();
	}

	@PreUpdate
	private void preUpdate() {
		updatedAt = Instant.now();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

	public void carregarEmpregos(List<EmpregoRequest> empregos) {
		empregos.forEach(e -> this.empregos.add(new Emprego(e, this)));
	}

    public void  atualizar(ClientRequest clientRequest) {
    	name = clientRequest.getName();
		cpf = clientRequest.getCpf();
		birthDate = clientRequest.getBirthDate();
		children = clientRequest.getChildren();

		empregos.clear();
		carregarEmpregos(clientRequest.getEmpregos());
	}
}
