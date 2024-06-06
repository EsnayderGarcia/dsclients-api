package com.snayder.dsclients.relatorio;

import com.snayder.dsclients.cliente.Client;
import com.snayder.dsclients.cliente.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.snayder.dsclients.util.Converter.localdateToString;

@Service
public class RelatorioService {
    private final ClientRepository clientRepository;

    private static final String CAMINHO_RELATORIO_CLIENTES = "/relatorios/cliente/relatorioClientes.jrxml";
    private static final String CAMINHO_RELATORIO_DETALHES_CLIENTES = "/relatorios/cliente/relatorioDetalhesCliente.jrxml";

    public RelatorioService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public byte[] gerarPdfRelatorioClientes() {
        InputStream stream = RelatorioService.class.getResourceAsStream(CAMINHO_RELATORIO_CLIENTES);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dataEmissao", new Date());

        return RelatorioUtil.gerarRelatorio(stream, parametros, clientRepository.findAll());
    }

    @Transactional(readOnly = true)
    public byte[] gerarPdfRelatorioDetalhesCliente(Long clientId) {
        Client client = clientRepository.findById(clientId).get();

        InputStream stream = RelatorioService.class.getResourceAsStream(CAMINHO_RELATORIO_DETALHES_CLIENTES);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("dataEmissao", new Date());
        parametros.put("nome", client.getName());
        parametros.put("cpf", client.getCpfCnpj());
        parametros.put("dataNascimento", localdateToString(client.getBirthDate()));

        return RelatorioUtil.gerarRelatorio(stream, parametros, client.getEmpregos());
    }
}
