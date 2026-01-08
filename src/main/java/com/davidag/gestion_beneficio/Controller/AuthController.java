package com.davidag.gestion_beneficio.Controller;

import com.davidag.gestion_beneficio.Data.AuthResponse;
import com.davidag.gestion_beneficio.Data.CreateUserRequest;
import com.davidag.gestion_beneficio.Data.LoginRequest;
import com.davidag.gestion_beneficio.Data.RegisterRequest;
import com.davidag.gestion_beneficio.Data.RegisterResponse;
import com.davidag.gestion_beneficio.Data.ResetPasswordRequest;
import com.davidag.gestion_beneficio.Service.Jwt.JwtService;
import com.davidag.gestion_beneficio.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/create-user")
    public ResponseEntity<AuthResponse> createUser(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestParam(value = "registerToken", required = false) String registerTokenParam,
            @RequestBody CreateUserRequest req
    ) {
        
        String token = jwtService.extractBearer(authorization);
        if (token == null || token.isBlank()) {
            token = registerTokenParam; 
        }
        return ResponseEntity.ok(authService.createUser(token, req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequest req){

        authService.resetPassword(req);
        
        return ResponseEntity.ok().build();

    } 
   
    
}
