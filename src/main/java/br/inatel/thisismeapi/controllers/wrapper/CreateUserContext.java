package br.inatel.thisismeapi.controllers.wrapper;

import br.inatel.thisismeapi.entities.User;

public class CreateUserContext {
    private User user;
    private String verifyPassword;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
