package com.davidag.gestion_beneficio.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String landing() {
        return "landing";   
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/create-password")
    public String createPasswordPage() {
        return "create-password";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";    
    }
    
}
