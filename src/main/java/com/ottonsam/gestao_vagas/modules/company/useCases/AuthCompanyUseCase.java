package com.ottonsam.gestao_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ottonsam.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.ottonsam.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import com.ottonsam.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secrete}")
    private String secreteToken;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO auth) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(auth.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("User or password incorrect");
        });

        if (!this.passwordEncoder.matches(auth.getPassword(), company.getPassword())) {
            throw new AuthenticationException("User or password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secreteToken);
        
        var token = JWT.create().withIssuer("gestao-vagas")
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .withSubject(company.getId().toString())
            .withClaim("roles", Arrays.asList("company"))
            .sign(algorithm);
    
        return AuthCompanyResponseDTO.builder()
            .accessToken(token)
            .build();
    }
}
