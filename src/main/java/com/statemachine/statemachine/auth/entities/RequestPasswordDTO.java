package com.statemachine.statemachine.auth.entities;

public class RequestPasswordDTO {

    private String email;

    public RequestPasswordDTO(String email) {
        this.email = email;
    }

    public RequestPasswordDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
