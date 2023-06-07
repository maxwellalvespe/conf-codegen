package com.api.security.resource;


import com.api.security.auth.TokenServiceImpl;
import com.api.security.model.Login;
import com.api.security.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class GetTokenLogin {

    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServiceImpl tokenService;

    public GetTokenLogin(AuthenticationManager auth) {

        this.authenticationManager = auth;

    }

    @PostMapping
    public ResponseEntity<Map<String, String>> getToken(@RequestBody Login login) {
        var respose = new HashMap<String, String>();


        var retorno = new UsernamePasswordAuthenticationToken(login.getUser(), login.getPassword());

        var authOk = this.authenticationManager.authenticate(retorno);
        var resp = (Usuario) authOk.getPrincipal();

        respose.put("token", tokenService.gerarToken(resp));


        return new ResponseEntity<>(respose, HttpStatus.OK);
    }
}
