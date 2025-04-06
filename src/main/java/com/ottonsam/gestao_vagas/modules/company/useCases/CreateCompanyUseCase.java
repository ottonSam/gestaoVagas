package com.ottonsam.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ottonsam.gestao_vagas.exceptions.UserFoundException;
import com.ottonsam.gestao_vagas.modules.company.entites.CompanyEntity;
import com.ottonsam.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity company) {
        this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
            .ifPresent(c -> {
                throw new UserFoundException();
            });
        company.setPassword(this.passwordEncoder.encode(company.getPassword()));
        return this.companyRepository.save(company);
    }
}
