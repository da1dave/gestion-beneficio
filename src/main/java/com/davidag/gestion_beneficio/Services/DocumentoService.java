package com.davidag.gestion_beneficio.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.davidag.gestion_beneficio.Enum.TipoDocBen;
import com.davidag.gestion_beneficio.Model.Documento;
import com.davidag.gestion_beneficio.Model.Usuario;
import com.davidag.gestion_beneficio.Repo.RepoDocumento;

@Service
public class DocumentoService {

    @Autowired
    private RepoDocumento repodoc;

    @Autowired
    private SpaceStorageService spaceserv;
    @Autowired
    private UsuarioService usuarioserv;

    public Documento subirDocumentoUsuario(TipoDocBen tipodocben,  MultipartFile archivo){

        Usuario user = usuarioserv.currentUser();

        if (repodoc.existsByUsuarioAndTipodocben(user, tipodocben)){

            throw new RuntimeException("El documento ya fue cargado...");

        }

        String key;
        try{
        
        key = spaceserv.subirDocumento(archivo, user.getId(), tipodocben.name());
        
        }catch (IOException e){

            throw new RuntimeException("Error al subir el archivo...", e);

        }

        Documento doc = new Documento();
        doc.setUsuario(user);
        doc.setTipodocben(tipodocben);
        doc.setNombre_archivo(archivo.getOriginalFilename());
        doc.setKeyspaces(key);
        doc.setRuta_archivo(key);    

        return repodoc.save(doc);

    }

    public List<Documento> verMisDocumentos(){

        String numdoc = usuarioserv.currentUserNumDoc();

        return repodoc.findByUsuarioNumdoc(numdoc);
        
    }


    
}
