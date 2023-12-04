package com.dggl.amei.controllers;

import com.dggl.amei.configuration.security.services.UserDetailsImpl;
import com.dggl.amei.dtos.requests.NovoOrcamentoRequest;
import com.dggl.amei.dtos.requests.PeriodoDTO;
import com.dggl.amei.dtos.requests.PeriodoDateTimeDTO;
import com.dggl.amei.dtos.requests.UpdateOrcamentoRequest;
import com.dggl.amei.dtos.responses.OrcamentoResponseDTO;
import com.dggl.amei.dtos.responses.relatorios.ExportacaoOrcamentoDTO;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.services.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/orcamentos")
public class  OrcamentoController extends AbstractController {

    @Autowired
    private OrcamentoService service;

    @GetMapping
    public ResponseEntity<Page<OrcamentoResponseDTO>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication
    ){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Page<Orcamento> orcamentos = service.findAll(filter, PageRequest.of(page, size), userDetails.getId());

        return ResponseEntity.ok().body(OrcamentoResponseDTO.fromEntity(orcamentos));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Orcamento> findById(@PathVariable Long id){
        Orcamento obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/emitirOrc/{id}")
    public ResponseEntity<ExportacaoOrcamentoDTO> emissaoRel(@PathVariable Long id) {
        var orcamento = service.findById(id);

        return ResponseEntity.ok().body(ExportacaoOrcamentoDTO.fromEntity(orcamento));
    }

    @PostMapping(value = "/downloadCsvPorDatas")
    public void exportaOrcamentoParaCsvPorPeriodo(
            Authentication authentication,
            HttpServletResponse servletResponse, @RequestBody PeriodoDateTimeDTO dto) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Contente-Disposition", "attachment; filename=\"Orcamentos_por_periodo.csv\"");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        service.exportaOrcamentoParaCsvPorPeriodo(userDetails.getId(), servletResponse.getWriter(), dto.getDataInicio(), dto.getDataFim());

    }

    @GetMapping(value = "/downloadCsv")
    public void exportaOrcamentoParaCsv(
            Authentication authentication,
            HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"Orcamentos.csv\"");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        service.exportaOrcamentoParaCsv(userDetails.getId(), servletResponse.getWriter());
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
