package com.boot.SpringBoot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.SpringBoot.model.UserDetail;
import com.boot.SpringBoot.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepo repo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail userDetail = repo.findByUsername(username);
        if (userDetail == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return userDetail;
    
}
}
