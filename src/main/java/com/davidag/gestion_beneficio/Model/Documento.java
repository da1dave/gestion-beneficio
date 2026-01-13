package com.davidag.gestion_beneficio.Model;

import java.time.LocalDateTime;

import com.davidag.gestion_beneficio.Enum.EstadoDoc;
import com.davidag.gestion_beneficio.Enum.TipoDocBen;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EstadoDoc estadodoc = EstadoDoc.PENDIENTE;

    @Enumerated(EnumType.STRING)
    private TipoDocBen tipodocben;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", nullable= false)
    private Beneficiario beneficiario;

    private String keyspaces;   

    @Column(nullable = false)
    private String nombre_archivo;

    @Column(nullable = false)
    private String ruta_archivo;

    private LocalDateTime fechasubida = LocalDateTime.now();

   
}
