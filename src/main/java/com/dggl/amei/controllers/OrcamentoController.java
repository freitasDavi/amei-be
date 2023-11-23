package com.dggl.amei.controllers;

import com.dggl.amei.dtos.requests.NovoOrcamentoRequest;
import com.dggl.amei.dtos.requests.PeriodoDTO;
import com.dggl.amei.dtos.requests.UpdateOrcamentoRequest;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.services.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/orcamentos")
public class  OrcamentoController extends AbstractController {

    @Autowired
    private OrcamentoService service;

    @GetMapping
    public ResponseEntity<Page<Orcamento>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Orcamento> orcamentos = service.findAll(filter, PageRequest.of(page, size));

        return ResponseEntity.ok().body(orcamentos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Orcamento> findById(@PathVariable Long id){
        Orcamento obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/downloadCsvPorDatas")
    public void exportaOrcamentoParaCsvPorPeriodo(HttpServletResponse servletResponse, @RequestBody PeriodoDTO dto) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Contente-Disposition", "attachment; filename=\"orcamentos.csv\"");

        service.exportaOrcamentoParaCsvPorPeriodo(servletResponse.getWriter(), dto.getDataInicio(), dto.getDataFim());

    }

    @RequestMapping(value = "/api/orcamentos/download")
    public void exportaOrcamentoParaCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"orcamentos.csv\"");
        service.exportaOrcamentoParaCsv(servletResponse.getWriter());
    }


    @PostMapping
    public ResponseEntity<Orcamento> insert(@RequestBody NovoOrcamentoRequest orcamento){
        var orcamentoSalvo = service.insert(orcamento);

        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orcamento.getId()).toUri();

        return ResponseEntity.ok(orcamentoSalvo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateOrcamentoRequest orcamento){
        service.update(id, orcamento);
        return ResponseEntity.ok().build();
    }

}
