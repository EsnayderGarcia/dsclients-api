package com.snayder.dsclients.cliente;

import com.snayder.dsclients.emprego.EmpregoRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientRequest {
	private String name;
	
	private String cpf;

	private LocalDate birthDate;
	
	private Integer children;

	private List<EmpregoRequest> empregos = new ArrayList<>();
	
	public ClientRequest() {
	}

	public ClientRequest(Client client) {
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.birthDate = client.getBirthDate();
		this.children = client.getChildren();
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

	public List<EmpregoRequest> getEmpregos() {
		return empregos;
	}
}
