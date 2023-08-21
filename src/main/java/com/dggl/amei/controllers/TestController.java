package com.dggl.amei.controllers;

import com.dggl.amei.models.Test;
import com.dggl.amei.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testes")
public class TestController {

    @Autowired
    TestRepository testRepository;

    @PostMapping
    public ResponseEntity<?> newTest () {
        var test = new Test();

        testRepository.save(test);

        return ResponseEntity.ok(test);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(testRepository.findAll());
    }
}
