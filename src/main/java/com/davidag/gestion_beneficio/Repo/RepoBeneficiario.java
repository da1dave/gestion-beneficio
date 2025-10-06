package com.davidag.gestion_beneficio.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidag.gestion_beneficio.Enum.TipoDoc;
import com.davidag.gestion_beneficio.Model.Beneficiario;

public interface RepoBeneficiario extends JpaRepository<Beneficiario, Integer>{

     Optional<Beneficiario> findByTipodocumentoAndNumerodocumento(
            TipoDoc tipodocumento,
            String numerodocumento
    );

}
