package com.davidag.gestion_beneficio.Data;
import com.davidag.gestion_beneficio.Enum.TipoDoc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull private TipoDoc tipodocumento;
    @NotBlank private String numdoc;
    @NotNull private LocalDate fechaexpdoc;
    @NotNull private LocalDate fechanac;

}
