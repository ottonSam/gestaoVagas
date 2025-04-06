package com.ottonsam.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ottonsam.gestao_vagas.modules.company.entites.JobEntity;
import com.ottonsam.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity job) {
        return this.jobRepository.save(job);
    }
    
}
