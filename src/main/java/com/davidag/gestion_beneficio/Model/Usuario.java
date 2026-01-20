package com.davidag.gestion_beneficio.Model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.davidag.gestion_beneficio.Enum.Rol;
import com.davidag.gestion_beneficio.Enum.TipoDoc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ForeignKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=10)
    private TipoDoc tipodocumento;
    @Column(nullable=false, length=30)
    private String numdoc;
    private LocalDate fechaexpdoc;
    private LocalDate fechanac;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "beneficiary_id", foreignKey = @ForeignKey(name="fk_user_beneficiary"))
    private Beneficiario beneficiario;
    @Enumerated(EnumType.STRING)
    private Rol roles;
    @CreationTimestamp @Column(updatable=false)
    private Instant creadoen;
    @UpdateTimestamp 
    private Instant actualizadoen;

    @Column(name = "password_hash", nullable = false, length = 200)
    private String passwordHash;

    @Column(nullable = false)
    private boolean activo = true;

    @OneToMany(mappedBy =  "beneficiario", fetch = FetchType.LAZY)  
    private List<Documento> documentos;
      
    
}
