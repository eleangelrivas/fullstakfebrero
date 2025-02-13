package com.elengel.api.fullstack.dto;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
