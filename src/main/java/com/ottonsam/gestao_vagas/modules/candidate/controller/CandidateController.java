package com.ottonsam.gestao_vagas.modules.candidate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ottonsam.gestao_vagas.modules.candidate.CandidateEntity;
import com.ottonsam.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
