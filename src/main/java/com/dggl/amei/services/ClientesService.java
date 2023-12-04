package com.dggl.amei.services;

import com.dggl.amei.dtos.responses.ClienteResponseDTO;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Clientes;
import com.dggl.amei.models.QClientes;
import com.dggl.amei.models.User;
import com.dggl.amei.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository repository;

    public Optional<Clientes> findById(Long id) {
        return repository.findById(id);
    }

    public Page<Clientes> findAll(String filter, Pageable pageable, Long id){
        return repository.findAll(filter, Clientes.class, pageable,
                QClientes.clientes.usuarioCliente.id.eq(id));
    }
    public void create (ClienteResponseDTO dto) {
        var cliente = dto.toEntity();

        repository.save(cliente);
    }
    public Clientes update (Long id, ClienteResponseDTO dto) {
        var dbObject = repository.findById(id);

        if (dbObject.isEmpty()) throw new RecursoNaoEncontrado("Cliente", id);

        var entidade = dbObject.get();

        dto.toEntity(entidade);
        repository.save(entidade);

        return entidade;
    }

    public Clientes getClientePadrao(Long id) {
        var clientePadrao = repository.findAll(QClientes.clientes.nomeCliente.like("%padrão%").and(QClientes.clientes.usuarioCliente.id.eq(id))).stream().findFirst();

        if (clientePadrao.isEmpty()) {
            throw new RecursoNaoEncontrado("Cliente", 1L);
        }

        return clientePadrao.get();
    }

    public void createClientePadrao(Long id) {
        var cliente = new Clientes();
        cliente.setNomeCliente("Cliente padrão");
        cliente.setUsuarioCliente(new User(id));
        cliente.setCnpjCliente("00000000000");
        cliente.setTelefoneCliente("00000000000");
        cliente.setEnderecoCliente("Rua do cliente padrão");
        cliente.setCepCliente("00000000");
        //cliente.setCidadeCliente("Cidade do cliente padrão");
        //cliente.setEstadoCliente("Estado do cliente padrão");
        cliente.setEmailCliente("cliente@padrao.com");
        cliente.setCepCliente("00000000");
        cliente.setComplementoCliente("");

        repository.save(cliente);
    }

    public String buscaNomeDoClientePeloId(Long id){
        return repository.findById(id)
                .map(Clientes :: getNomeCliente)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));

    }
}
