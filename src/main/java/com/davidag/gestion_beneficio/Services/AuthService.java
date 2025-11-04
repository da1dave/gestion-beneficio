package com.davidag.gestion_beneficio.Services;

import com.davidag.gestion_beneficio.Data.AuthResponse;
import com.davidag.gestion_beneficio.Data.CreateUserRequest;
import com.davidag.gestion_beneficio.Data.LoginRequest;
import com.davidag.gestion_beneficio.Data.RegisterRequest;
import com.davidag.gestion_beneficio.Data.RegisterResponse;
import com.davidag.gestion_beneficio.Enum.TipoDoc;
import com.davidag.gestion_beneficio.Model.Beneficiario;
import com.davidag.gestion_beneficio.Model.Usuario;
import com.davidag.gestion_beneficio.Repo.RepoBeneficiario;
import com.davidag.gestion_beneficio.Repo.RepoUsuario;
import com.davidag.gestion_beneficio.Service.Jwt.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RepoBeneficiario repoBeneficiario;
    private final RepoUsuario repoUsuario;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    
    public RegisterResponse register(RegisterRequest req) {

    
        Beneficiario beneficiario = repoBeneficiario
                .findByTipodocumentoAndNumerodocumento(req.getTipodocumento(), req.getNumdoc())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiario no encontrado"));
        
        if (!req.getFechaexpdoc().equals(beneficiario.getFechaexpdoc()))
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Fecha de expedición no coincide");
        if (!req.getFechanac().equals(beneficiario.getFechanac()))
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Fecha de nacimiento no coincide");

       
        String subject = "REG:" + req.getTipodocumento().name() + ":" + req.getNumdoc();

        Map<String, Object> claims = new HashMap<>();
        claims.put("tipo", req.getTipodocumento().name());
        claims.put("numdoc", req.getNumdoc());
        claims.put("beneficiarioId", beneficiario.getId());
        claims.put("ts", new Date().getTime());

        String registerToken = jwtService.generateToken(subject, claims);
        return new RegisterResponse(registerToken);
    }


    public AuthResponse createUser(String registerToken, CreateUserRequest req) {
        if (registerToken == null || registerToken.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "registerToken es obligatorio");
        }

   
        if (!jwtService.isTokenValid(registerToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token de registro inválido");
        }

       
        String subject = jwtService.extractSubject(registerToken);
        if (subject == null || !subject.startsWith("REG:")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token de registro inválido");
        }

        String[] parts = subject.split(":");
        if (parts.length != 3) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token de registro inválido");
        }

        TipoDoc tipoDoc = TipoDoc.valueOf(parts[1]);
        String numDoc = parts[2];


        Optional<Usuario> yaExiste = repoUsuario.findByTipodocumentoAndNumdoc(tipoDoc, numDoc);
        if (yaExiste.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe");
        }

    
        Beneficiario beneficiario = repoBeneficiario
                .findByTipodocumentoAndNumerodocumento(tipoDoc, numDoc)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiario no encontrado"));


        Usuario u = new Usuario();
        u.setTipodocumento(tipoDoc);
        u.setNumdoc(numDoc);
        u.setBeneficiario(beneficiario);
  
        try { u.setFechanac(beneficiario.getFechanac()); } catch (Exception ignored) {}
        try { u.setFechaexpdoc(beneficiario.getFechaexpdoc()); } catch (Exception ignored) {}

        u.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        u.setActivo(true); 

        repoUsuario.save(u);

   
        String sessionSubject = tipoDoc.name() + ":" + numDoc;
        String jwt = jwtService.generateToken(sessionSubject, Map.of());
        return new AuthResponse(jwt);
    }
    public AuthResponse login(LoginRequest req) {

        Usuario usuario = repoUsuario
                .findByTipodocumentoAndNumdoc(req.getTipodocumento(), req.getNumdoc())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        if (!usuario.isActivo() || !passwordEncoder.matches(req.getPassword(), usuario.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

     
        String subject = usuario.getTipodocumento().name() + ":" + usuario.getNumdoc();
        String jwt = jwtService.generateToken(subject, Map.of());
        return new AuthResponse(jwt);
    }
}
