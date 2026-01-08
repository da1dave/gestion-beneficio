package com.davidag.gestion_beneficio.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidag.gestion_beneficio.Model.Documento;

@Repository
public interface RepoDocumento extends JpaRepository<Documento, Integer>{

    List<Documento> findByBeneficiarioId(Integer Beneficiario);
    
}
