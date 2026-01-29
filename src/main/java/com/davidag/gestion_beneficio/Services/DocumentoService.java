package com.davidag.gestion_beneficio.Services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.davidag.gestion_beneficio.Enum.EstadoDoc;
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

    //--SERVICIOS DE ADMIN--//

    public List<Documento> verTodosLosDocumentos(){

        return repodoc.findAll();
    }

    //--ADMIN: Filtrar--//

    public List<Documento> filtrarDocs( EstadoDoc estadodoc, TipoDocBen tipodocben, String numerodoc){
        
        if(estadodoc != null && tipodocben != null){

            return repodoc.findByEstadodocAndTipodocben(estadodoc, tipodocben);
        }
        if(estadodoc != null){

            return repodoc.findByEstadodoc(estadodoc);
        }
        if (tipodocben != null){

            return repodoc.findByTipodocben(tipodocben);
        }
        if (numerodoc != null){

            return repodoc.findByUsuarioNumdoc(numerodoc);
        }

        return repodoc.findAll();
    
    }

    //ADMIN: buscar documentos por nombre o apellido
    public List<Documento> buscarPorNombre(String nombre){
        
        if(nombre == null || nombre.isBlank()){

           return repodoc.findAll();
        }

        List<Documento> pornombre = repodoc.findByUsuario_Beneficiario_NombreContainingIgnoreCase(nombre);

        List<Documento> porapellido = repodoc.findByUsuario_Beneficiario_ApellidoContainingIgnoreCase(nombre);

        return Stream.concat(pornombre.stream(), porapellido.stream())
                     .distinct()
                     .toList();             

    }

    

    
}
