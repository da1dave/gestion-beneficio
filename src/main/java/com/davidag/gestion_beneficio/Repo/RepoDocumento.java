package com.davidag.gestion_beneficio.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidag.gestion_beneficio.Model.Documento;
import com.davidag.gestion_beneficio.Model.Usuario;
import com.davidag.gestion_beneficio.Enum.EstadoDoc;
import com.davidag.gestion_beneficio.Enum.TipoDocBen;



@Repository
public interface RepoDocumento extends JpaRepository<Documento, Integer>{

    boolean existsByUsuarioAndTipodocben(Usuario usuario, TipoDocBen tipodocben);

    List<Documento> findByUsuarioNumdoc(String numerodocumento);

    List<Documento> findByEstadodoc(EstadoDoc estadodoc);

    List<Documento> findByTipodocben(TipoDocBen tipodocben);

    List<Documento> findByEstadodocAndTipodocben(EstadoDoc estadodoc, TipoDocBen tipodocben);

    List<Documento> findByUsuario_Beneficiario_NombreContainingIgnoreCase(String nombre);

    List<Documento> findByUsuario_Beneficiario_ApellidoContainingIgnoreCase(String nombre);



    
}
