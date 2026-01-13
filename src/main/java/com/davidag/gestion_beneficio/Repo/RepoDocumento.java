package com.davidag.gestion_beneficio.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidag.gestion_beneficio.Model.Documento;
import com.davidag.gestion_beneficio.Enum.EstadoDoc;
import com.davidag.gestion_beneficio.Enum.TipoDocBen;



@Repository
public interface RepoDocumento extends JpaRepository<Documento, Integer>{

    List<Documento> findByBeneficiarioId(Integer Beneficiario);

    List<Documento> findByEstadodoc(EstadoDoc estadodoc);

    List<Documento> findByTipodocben(TipoDocBen tipodocben);

    boolean existsByBeneficiarioIdAndTipodocben(Integer beneficiarioId, TipoDocBen tipoDocBen);
    
}
