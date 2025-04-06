package com.ottonsam.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ottonsam.gestao_vagas.exceptions.UserFoundException;
import com.ottonsam.gestao_vagas.modules.candidate.entities.CandidateEntity;
import com.ottonsam.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {
     
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
            .ifPresent(c -> {
                throw new UserFoundException();
            });
        candidate.setPassword(this.passwordEncoder.encode(candidate.getPassword()));
        return this.candidateRepository.save(candidate);
    }
    
}
