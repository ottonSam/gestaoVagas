package com.ottonsam.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ottonsam.gestao_vagas.exceptions.UserFoundException;
import com.ottonsam.gestao_vagas.modules.company.entites.CompanyEntity;
import com.ottonsam.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;
    
    public CompanyEntity execute(CompanyEntity company) {
        this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
            .ifPresent(c -> {
                throw new UserFoundException();
            });
        return this.companyRepository.save(company);
    }
}
