package br.inatel.thisismeapi.controllers.wrapper;

import java.io.Serializable;

public class VerifyResetContext implements Serializable {

    private String email;

    private String number;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
