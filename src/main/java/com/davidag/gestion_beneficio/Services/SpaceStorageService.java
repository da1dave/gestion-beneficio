package com.davidag.gestion_beneficio.Services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class SpaceStorageService {

    @Autowired
    private S3Client s3client;

    @Value("${do.spaces.bucket}")
    private String bucket;

    public String subirDocumento(MultipartFile archivo, Integer benfid, String tipodoc) throws IOException{

        String key = "beneficiarios/"+benfid+"/"+tipodoc+".pdf";

        PutObjectRequest request = PutObjectRequest.builder()
                                                         .bucket(bucket)
                                                         .key(key)
                                                         .contentType("application/pdf")
                                                         .build();
        s3client.putObject(request, RequestBody.fromInputStream(archivo.getInputStream(), archivo.getSize()));

        return key;

    }

    public ResponseInputStream<?> descargarDoc(String key){

        GetObjectRequest request = GetObjectRequest.builder()
                                                   .bucket(bucket)
                                                   .key(key)
                                                   .build();
        
        return s3client.getObject(request);
    }
    
}
