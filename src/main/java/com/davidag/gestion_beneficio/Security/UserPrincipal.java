package com.davidag.gestion_beneficio.Security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.davidag.gestion_beneficio.Model.Usuario;
import com.davidag.gestion_beneficio.Enum.Rol;


public class UserPrincipal implements UserDetails {

    @Autowired
    private Usuario user;

    public UserPrincipal(Usuario user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Rol rolEnum = user.getRoles(); 
        String rol = (rolEnum == null) ? "BENEFICIARIO" : rolEnum.name();
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol));
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash(); 
    }

    @Override
    public String getUsername() {

        return  user.getNumdoc();
    }

    @Override public boolean isAccountNonExpired()  { return true; }
    @Override public boolean isAccountNonLocked()   { return true; }
    @Override public boolean isCredentialsNonExpired(){ return true; }
    @Override public boolean isEnabled()            { return user.isActivo(); } 
}

