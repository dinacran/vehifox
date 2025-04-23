package com.boot.SpringBoot.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Component
@Data
@Entity
public class UserDetail implements UserDetails{

    @Id
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(isAdmin)
        return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
        return Collections.singleton(new SimpleGrantedAuthority("USER"));

    }
}
