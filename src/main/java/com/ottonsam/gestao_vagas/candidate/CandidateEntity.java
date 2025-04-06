package com.ottonsam.gestao_vagas.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;

    @Pattern(regexp = "\\S+", message = "Username cannot be spaces")
    private String username;  

    @Email(message = "Email should be valid")
    private String email;

    @Length(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private String description;
    private String curriculum;
    
}
