package com.example.wheelworld.models;


public class LOginResponse {
    private String token ;

    public Utilisateur currentUser;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LOginResponse(String token) {
        this.token = token;
    }
}
