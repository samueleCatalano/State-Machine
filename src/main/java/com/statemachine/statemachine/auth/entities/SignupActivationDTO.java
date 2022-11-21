package com.statemachine.statemachine.auth.entities;


public class SignupActivationDTO {

    private String activationCode;

    public SignupActivationDTO(String activationCode) {
        this.activationCode = activationCode;
    }

    public SignupActivationDTO() {
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
