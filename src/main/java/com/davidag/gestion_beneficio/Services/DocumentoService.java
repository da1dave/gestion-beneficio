package com.davidag.gestion_beneficio.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidag.gestion_beneficio.Repo.RepoBeneficiario;
import com.davidag.gestion_beneficio.Repo.RepoDocumento;

@Service
public class DocumentoService {

    @Autowired
    private RepoDocumento repodoc;
    @Autowired
    private RepoBeneficiario repoben;
    @Autowired
    private SpaceStorageService spaceserv;
    
}
