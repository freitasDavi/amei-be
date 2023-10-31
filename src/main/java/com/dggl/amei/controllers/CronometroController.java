package com.dggl.amei.controllers;

import com.dggl.amei.models.Cronometro;
import com.dggl.amei.services.CronometroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cronometro")
public class CronometroController {

    private final CronometroService service;

    public CronometroController(CronometroService service) {
        this.service = service;
    }

    @GetMapping("/{codigoUsuario}")
    public ResponseEntity<List<Cronometro>> getTodos (@PathVariable Long codigoUsuario) {
        var cronometros = service.getAllByUserId(codigoUsuario);

        return ResponseEntity.ok(cronometros);
    }

    @GetMapping("/ultimoAtivo/{codigoUsuario}")
    public ResponseEntity<Cronometro> getUltimoAtivo (@PathVariable Long codigoUsuario) {
        var cron = service.getUltimoCronometroAtivo(codigoUsuario);

        return ResponseEntity.ok(cron);
    }

    @PutMapping("/pararCronometro/{id}")
    public ResponseEntity<Void> pararCronometro (@PathVariable Long id) {
        service.stopCronometro(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Cronometro novoCronometro) {
        service.create(novoCronometro);

        return ResponseEntity.ok().build();
    }
}
