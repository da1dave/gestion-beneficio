package com.davidag.gestion_beneficio.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class SpacesConfig {

    @Bean
    public S3Client s3client(
            @Value("${do.spaces.key}") String key,
            @Value("${do.spaces.secret}") String secret,
            @Value("${do.spaces.endpoint}") String endpoint,
            @Value("${do.spaces.region}") String region

    ){
         return S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(key, secret)
                        )
                )
                .build();
    }
    
}
