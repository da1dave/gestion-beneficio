package com.davidag.gestion_beneficio.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    
    private String registerToken;

    @NotBlank
    @Size(min = 8, max = 72)
    private String password;
}
