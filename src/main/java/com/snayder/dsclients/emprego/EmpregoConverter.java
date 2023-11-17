package com.snayder.dsclients.emprego;

import com.snayder.dsclients.cliente.Client;

public class EmpregoConverter {
    public static Emprego toEmprego(EmpregoRequest empregoRequest, Client client) {
        Emprego emprego = new Emprego();

        emprego.setId(empregoRequest.getId());
        emprego.setCargo(empregoRequest.getCargo());
        emprego.setDescricao(empregoRequest.getDescricao());
        emprego.setSalario(empregoRequest.getSalario());
        emprego.setClient(client);

        return emprego;
    }

    public static EmpregoResponse toEmpregoResponse(Emprego emprego) {
        EmpregoResponse empregoResponse = new EmpregoResponse();

        empregoResponse.setId(emprego.getId());
        empregoResponse.setCargo(emprego.getCargo());
        empregoResponse.setDescricao(emprego.getDescricao());
        empregoResponse.setSalario(emprego.getSalario());

        return empregoResponse;
    }
}
