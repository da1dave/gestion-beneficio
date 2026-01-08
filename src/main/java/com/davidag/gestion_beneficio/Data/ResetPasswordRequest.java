package com.davidag.gestion_beneficio.Data;

import java.time.LocalDate;

import com.davidag.gestion_beneficio.Enum.TipoDoc;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    @NotNull 
    private TipoDoc tipodocumento;
    @NotBlank 
    private String numdoc;
    @NotNull 
    private LocalDate fechaexpdoc;
    @NotNull 
    private LocalDate fechanac;
    @NotBlank @Size(min=8, max=72) 
    private String newpassword;
    
    
}
