package com.ottonsam.gestao_vagas.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ottonsam.gestao_vagas.candidate.CandidateEntity;
import com.ottonsam.gestao_vagas.candidate.CandidateRepository;
import com.ottonsam.gestao_vagas.exceptions.UserFoundException;

@Service
public class CreateCandidateUseCase {

     @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
            .ifPresent(c -> {
                throw new UserFoundException();
            });
        return this.candidateRepository.save(candidate);
    }
    
}
