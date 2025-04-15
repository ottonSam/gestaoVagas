package com.ottonsam.gestao_vagas.modules.candidate.useCases;

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
import com.ottonsam.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.ottonsam.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.ottonsam.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class AuthCandidateUseCase {
    
    @Value("${security.token.secrete.candidate}")
    private String secreteToken;

    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateDTO auth) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsernameOrEmail(auth.getUsername(), null).orElseThrow(() -> {
            throw new UsernameNotFoundException("User or password incorrect");
        });
        if (!this.passwordEncoder.matches(auth.getPassword(), candidate.getPassword())) {
            throw new UsernameNotFoundException("User or password incorrect");
        }

         Algorithm algorithm = Algorithm.HMAC256(secreteToken);
        
        var token = JWT.create().withIssuer("gestao-vagas")
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .withSubject(candidate.getId().toString())
            .withClaim("roles", Arrays.asList("candidate"))
            .sign(algorithm);
    
        return AuthCandidateResponseDTO.builder().accessToken(token).build();
    }

}
