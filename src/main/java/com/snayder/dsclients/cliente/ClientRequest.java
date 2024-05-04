package com.snayder.dsclients.cliente;

import com.snayder.dsclients.compatilhado.CpfCnpj;
import com.snayder.dsclients.emprego.EmpregoRequest;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientRequest {
	private String name;

	@NotBlank(message = "O campo documento é obrigatório")
	@CpfCnpj
	private String documento;

	private LocalDate birthDate;

	private Integer children;

	private final List<EmpregoRequest> empregos = new ArrayList<>();
	
	public ClientRequest() {
	}

	public ClientRequest(Client client) {
		this.name = client.getName();
		this.documento = client.getCpfCnpj();
		this.birthDate = client.getBirthDate();
		this.children = client.getChildren();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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
