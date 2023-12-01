package com.dggl.amei.controllers;

import com.dggl.amei.configuration.security.services.UserDetailsImpl;
import com.dggl.amei.dtos.requests.NovoOrdemServicoRequest;
import com.dggl.amei.dtos.requests.PeriodoDTO;
import com.dggl.amei.dtos.requests.UpdateOrdemServicoRequest;
import com.dggl.amei.dtos.responses.OrdemServicoResponseDTO;
import com.dggl.amei.dtos.responses.relatorios.ExportacaoOrdemDTO;
import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/ordemServico")
public class OrdermServicoController extends AbstractController {

    @Autowired
    private OrdemServicoService service;

    @GetMapping
    public ResponseEntity<Page<OrdemServicoResponseDTO>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication
    ){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Page<OrdemServico> listaOrdemServivo = service.findAll(filter, PageRequest.of(page, size), userDetails.getId());

        return ResponseEntity.ok().body(OrdemServicoResponseDTO.fromEntity(listaOrdemServivo));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> findById(@PathVariable Long id){
        OrdemServico obj = service.findById(id);

        return ResponseEntity.ok().body(OrdemServicoResponseDTO.fromEntity(obj));
    }

    @GetMapping(value = "/emitirOrdem/{id}")
    public ResponseEntity<ExportacaoOrdemDTO> emissaoRel(@PathVariable Long id) {
        var ordem = service.findById(id);

        return ResponseEntity.ok().body(ExportacaoOrdemDTO.fromEntity(ordem));
    }

    @PostMapping(value = "/downloadCsvPorDatas")
    public void exportaOrdemDeServicioParaCsvPorPeriodo(HttpServletResponse servletResponse, @RequestBody PeriodoDTO dto) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Contente-Disposition", "attachment; filename=\"ordensDeServico.csv\"");

        service.exportaOrdemDeServicoParaCsvPorPeriodo(servletResponse.getWriter(), dto.getDataInicio(), dto.getDataFim());

    }

    @GetMapping(value = "/downloadCsv")
    public void exportaOrdemDeServicoParaCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"ordensDeServico.csv\"");
        service.exportaOrdemDeServicoParaCsv(servletResponse.getWriter());
    }

    @PostMapping
    public ResponseEntity<OrdemServico> insert(@RequestBody NovoOrdemServicoRequest ordemServico){
        var ordemSalva = service.insert(ordemServico);

        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orcamento.getId()).toUri();

        return ResponseEntity.ok(ordemSalva);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateOrdemServicoRequest ordemServico){
        service.update(id, ordemServico);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/emitir/{id}")
    public ResponseEntity emitirNfe(@PathVariable Long id) {
        service.emitirNfe(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/gerarOrdem/{codigoOrcamento}")
    public ResponseEntity gerarOrdemDoOrcamento(@PathVariable Long codigoOrcamento) {
        var codigoOrdem = service.geraOrdemDeServicoVindoDoOrcamento(codigoOrcamento);

        return ResponseEntity.ok().body(codigoOrdem);
    }


}
