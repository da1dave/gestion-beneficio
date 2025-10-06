package com.davidag.gestion_beneficio.Data;
import com.davidag.gestion_beneficio.Enum.TipoDoc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    private TipoDoc tipodocumento;

    @NotBlank
    private String numdoc;

    @NotBlank
    private String password;
}
