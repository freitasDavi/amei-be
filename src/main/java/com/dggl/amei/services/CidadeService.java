package com.dggl.amei.services;

import com.dggl.amei.dtos.responses.RetornoCEP;
import com.dggl.amei.dtos.responses.ViaCEPRetorno;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Cidade;
import com.dggl.amei.repositories.BairroRepository;
import com.dggl.amei.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private BairroRepository bairroRepository;

    private String task = "Cidade";

    public Page<Cidade> findAll(String filter, Pageable pageable){

        return repository.findAll(filter, Cidade.class, pageable);
    }

    public Cidade findById(Long id){
        Optional<Cidade> cidade = repository.findById(id);
        return cidade.orElseThrow(()-> new RecursoNaoEncontrado(task, id));
    }

    public RetornoCEP findByCEP (String cep){
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://viacep.com.br/ws/"+cep+"/json/";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<?> result =
                restTemplate.exchange(uri, HttpMethod.GET, entity, ViaCEPRetorno.class);

        ViaCEPRetorno body = (ViaCEPRetorno) result.getBody();

        var cidade = repository.findByNomeCidadeContaining(body.getLocalidade());
        var bairro = bairroRepository.findFirstByNomeBairroContaining(body.getBairro());

        var retorno = new RetornoCEP(
                cidade.getId(),
                bairro.getId(),
                body.getLogradouro(),
                body.getComplemento()
        );

        return retorno;
    }

}
