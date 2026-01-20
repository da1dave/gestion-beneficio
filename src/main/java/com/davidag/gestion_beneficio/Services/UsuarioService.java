package com.davidag.gestion_beneficio.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.davidag.gestion_beneficio.Model.Usuario;
import com.davidag.gestion_beneficio.Repo.RepoUsuario;

@Service
public class UsuarioService {

    @Autowired
    private RepoUsuario repousuario;

    public String currentUserNumDoc(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getName();
    }

    public Usuario currentUser(){

        String numdoc = currentUserNumDoc();

        Usuario usuario = repousuario.findByNumdoc(numdoc)
                                     .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado..."));

        return usuario;

    }
    
}
