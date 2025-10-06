package com.davidag.gestion_beneficio.Model;


import java.time.LocalDate;
import com.davidag.gestion_beneficio.Enum.EstadoBeneficiario;
import com.davidag.gestion_beneficio.Enum.TipoBeneficio;
import com.davidag.gestion_beneficio.Enum.TipoDoc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiario {

    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING) @Column(nullable=false, length=10)
    private TipoDoc tipodocumento;

    @Column(nullable=false, length=30)
    private String numerodocumento;

    private String nombre;

    private String apellido;

    private LocalDate fechaexpdoc;

    private LocalDate fechanac;

    @Column(nullable=false, length=150)
    private String email;

    @Column(nullable=false, length=30)
    private String celular;

    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20)
    private TipoBeneficio tipobeneficio;

    @Column(nullable=false)
    private Integer añodeconvocatoria;

    @Enumerated(EnumType.STRING) @Column(nullable=false, length=30)
    private EstadoBeneficiario estado;
  
}
