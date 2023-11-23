package com.dggl.amei.controllers;

import com.dggl.amei.dtos.requests.AgendamentoRequestDTO;
import com.dggl.amei.dtos.requests.PeriodoDTO;
import com.dggl.amei.dtos.responses.AgendamentoResponseDTO;
import com.dggl.amei.models.Agendamento;
import com.dggl.amei.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/agendamentos")
public class AgendamentoController extends AbstractController {

    @Autowired
    private AgendamentoService service;

    @GetMapping("/ultimos/{codigoUsuario}")
    public ResponseEntity<List<AgendamentoResponseDTO>> getLatest (@PathVariable Long codigoUsuario) {
        List<Agendamento> listaAgendamentos = service.getLatestFive(codigoUsuario);

        return ResponseEntity.ok(AgendamentoResponseDTO.fromEntity(listaAgendamentos));
    }

    @GetMapping
    public ResponseEntity<Page<AgendamentoResponseDTO>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Agendamento> agendamentos = service.findAll(filter, PageRequest.of(page, size));

        return ResponseEntity.ok().body(AgendamentoResponseDTO.fromEntity(agendamentos));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AgendamentoResponseDTO> findById(@PathVariable Long id){
        Agendamento obj = service.findById(id);

        return ResponseEntity.ok().body(AgendamentoResponseDTO.fromEntity(obj));
    }

    @PostMapping(value = "/downloadCsvPorDatas")
    public void exportaAgendamentoParaCsvPorPeriodo(HttpServletResponse servletResponse, @RequestBody PeriodoDTO dto) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Contente-Disposition", "attachment; filename=\"agendamentos.csv\"");

        service.exportaAgendamentoParaCsvPorPeriodo(servletResponse.getWriter(), dto.getDataInicio(), dto.getDataFim());

    }

    @RequestMapping(value = "/api/agendamentos/download")
    public void exportaAgendamentoParaCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"agendamentos.csv\"");
        service.exportaAgendamentoParaCsv(servletResponse.getWriter());
    }

    @PostMapping
    public ResponseEntity<Agendamento> insert(@RequestBody AgendamentoRequestDTO obj){
        service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AgendamentoResponseDTO> update(@PathVariable Long id, @RequestBody AgendamentoRequestDTO obj){
        var entity  = service.update(id, obj);

        var dto = AgendamentoResponseDTO.fromEntity(entity);

        return ResponseEntity.ok().body(dto);
    }
}
