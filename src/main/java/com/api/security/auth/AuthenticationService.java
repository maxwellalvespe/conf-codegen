package com.api.security.auth;

import com.api.security.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();

        var ret = b.encode("123");

        System.out.println("---BCRYPT: " + ret);
        return new Usuario("maxwell", ret); //logica para buscar no banco
    }
}
