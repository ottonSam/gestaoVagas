package com.ottonsam.gestao_vagas.candidate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ottonsam.gestao_vagas.candidate.CandidateEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    
    @PostMapping("")
    public void create(@Valid @RequestBody CandidateEntity candidate) {
        
        return;
    }
    
}
