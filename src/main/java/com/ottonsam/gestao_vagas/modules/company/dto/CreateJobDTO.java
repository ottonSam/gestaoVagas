package com.ottonsam.gestao_vagas.modules.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateJobDTO {

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private String benefits;

    @NotBlank(message = "Level cannot be empty")
    private String level;
    
}
