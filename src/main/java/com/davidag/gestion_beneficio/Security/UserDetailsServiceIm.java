package com.davidag.gestion_beneficio.Security;

import com.davidag.gestion_beneficio.Enum.TipoDoc;
import com.davidag.gestion_beneficio.Model.Usuario;
import com.davidag.gestion_beneficio.Repo.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceIm implements UserDetailsService {

    @Autowired
    private RepoUsuario repoUsuario;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isBlank()) {
            throw new UsernameNotFoundException("Username vacío");
        }

        String tipoDocStr = null;
        String numDoc = username;

        if (username.contains(":")) {
            String[] parts = username.split(":", 2);
            tipoDocStr = parts[0];
            numDoc = parts[1];
        }

        Usuario user;

        if (tipoDocStr == null || tipoDocStr.isBlank()) {
       
            user = repoUsuario.findByNumdoc(numDoc)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        } else {
        
            final TipoDoc tipoEnum;
            try {
                tipoEnum = TipoDoc.valueOf(tipoDocStr);
            } catch (IllegalArgumentException e) {
                throw new UsernameNotFoundException("Tipo de documento inválido: " + tipoDocStr);
            }

            user = repoUsuario.findByTipodocumentoAndNumdoc(tipoEnum, numDoc)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        }

        return new UserPrincipal(user);
    }
}
