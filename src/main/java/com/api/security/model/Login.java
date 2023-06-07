package com.api.security.model;

public class Login {

    private String user;
    private String password;

    public Login(String nome, String senha) {
        this.user = nome;
        this.password = senha;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Login{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
