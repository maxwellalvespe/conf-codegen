package com.api.security.auth;

import com.api.security.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl {
    public String gerarToken(Usuario resp) {

        var retorno =  JWT.create()
                .withIssuer("usuarios")
                .withSubject(resp.getUser())
                .withClaim("id", resp.getPassword())
                .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("secret"));

        return retorno;
    }

    public String getSubject(String token) {

        return JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("usuarios")
                .build().verify(token)
                .getSubject();
    }
}
