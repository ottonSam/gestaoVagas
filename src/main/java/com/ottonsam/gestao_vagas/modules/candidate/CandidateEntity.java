package com.ottonsam.gestao_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidates")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @NotBlank(message = "Username cannot be empty")
    @Pattern(regexp = "\\S+", message = "Username cannot be spaces")
    private String username;  

    @Email(message = "Email should be valid")
    private String email;

    @Length(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private String description;
    private String curriculum;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}
