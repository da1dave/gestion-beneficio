package com.davidag.gestion_beneficio.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.davidag.gestion_beneficio.Enum.TipoDocBen;
import com.davidag.gestion_beneficio.Model.Documento;
import com.davidag.gestion_beneficio.Services.DocumentoService;

@RestController
@RequestMapping("api/documentos")
public class DocumentoController {

     @Autowired
     private DocumentoService docserv;


     @PostMapping("/subir")
     public ResponseEntity<Documento> postDocumentos( @RequestParam("tipodoc") TipoDocBen tipodoc,
                                                      @RequestParam("archivo") MultipartFile archivo){
          
        return ResponseEntity.ok(docserv.subirDocumentoUsuario(tipodoc, archivo));                                               
     }

     @GetMapping("/mis-documentos")
     public ResponseEntity<List<Documento>> getDocumentos(){

          return ResponseEntity.ok(docserv.verMisDocumentos());
          
     }

}

