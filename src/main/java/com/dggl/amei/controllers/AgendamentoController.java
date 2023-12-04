package com.dggl.amei.controllers;

import com.dggl.amei.configuration.security.services.UserDetailsImpl;
import com.dggl.amei.dtos.requests.AgendamentoRequestDTO;
import com.dggl.amei.dtos.requests.EmissaoRelBaseRequestDTO;
import com.dggl.amei.dtos.requests.PeriodoDTO;
import com.dggl.amei.dtos.responses.AgendamentoResponseDTO;
import com.dggl.amei.dtos.responses.relatorios.AgendamentoPorClienteDTO;
import com.dggl.amei.models.Agendamento;
import com.dggl.amei.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<Page<AgendamentoResponseDTO>> getLatest (@PathVariable Long codigoUsuario) {
        Page<Agendamento> listaAgendamentos = service.getLatestFive(codigoUsuario);

        return ResponseEntity.ok(AgendamentoResponseDTO.fromEntity(listaAgendamentos));
    }

    @GetMapping
    public ResponseEntity<Page<AgendamentoResponseDTO>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication
    ){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Page<Agendamento> agendamentos = service.findAll(filter, PageRequest.of(page, size), userDetails.getId());

        return ResponseEntity.ok().body(AgendamentoResponseDTO.fromEntity(agendamentos));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AgendamentoResponseDTO> findById(@PathVariable Long id){
        Agendamento obj = service.findById(id);

        return ResponseEntity.ok().body(AgendamentoResponseDTO.fromEntity(obj));
    }

    @PostMapping(value = "/downloadCsvPorDatas")
    public void exportaAgendamentoParaCsvPorPeriodo(
            Authentication authentication,
            HttpServletResponse servletResponse, @RequestBody PeriodoDTO dto) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Contente-Disposition", "attachment; filename=\"Agendamentos_por_periodo.csv\"");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        service.exportaAgendamentoParaCsvPorPeriodo(userDetails.getId(), servletResponse.getWriter(), dto.getDataInicio(), dto.getDataFim());

    }

    @GetMapping(value = "/downloadCsv")
    public void exportaAgendamentoParaCsv(
            Authentication authentication,
            HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"Agendamentos.csv\"");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        service.exportaAgendamentoParaCsv(userDetails.getId(), servletResponse.getWriter());
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


    @GetMapping(value = "/emitirRel/{codigoUsuario}")
    public ResponseEntity<List<AgendamentoPorClienteDTO>> emitirRelatorio(@PathVariable Long codigoUsuario){
        List<AgendamentoPorClienteDTO> dto = service.emitirRelatorio(codigoUsuario);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/emitirRel")
    public ResponseEntity<List<AgendamentoPorClienteDTO>> emitirRelatorio(
            @RequestBody EmissaoRelBaseRequestDTO request){
        List<AgendamentoPorClienteDTO> dto = service.emitirRelatorio(request.getCodigoUsuario(), request.getDataInicio(), request.getDataFim());

        return ResponseEntity.ok().body(dto);
    }
}
