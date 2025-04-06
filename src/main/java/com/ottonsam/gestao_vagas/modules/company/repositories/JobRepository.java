package com.ottonsam.gestao_vagas.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ottonsam.gestao_vagas.modules.company.entites.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    
}
