package com.davidag.gestion_beneficio.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidag.gestion_beneficio.Enum.TipoDoc;
import com.davidag.gestion_beneficio.Model.Beneficiario;
import com.davidag.gestion_beneficio.Model.Usuario;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNumdoc(String numdoc);
    Optional<Usuario> findByTipodocumentoAndNumdoc(TipoDoc tipodocumento, String numdoc);
    boolean existsByBeneficiario(Beneficiario b);

    
}
