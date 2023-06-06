package com.api.security.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class Rota {

    @GetMapping
    public ResponseEntity<String> getAuth() {
        return new ResponseEntity<>("Connection open!", HttpStatus.OK);
    }
}
