package br.inatel.thisismeapi.controllers.wrapper;

import br.inatel.thisismeapi.entities.dtos.UserDtoInput;
import com.google.gson.Gson;

import java.io.Serializable;

public class CreateUserContext implements Serializable {
    private UserDtoInput userDtoInput;
    private String verifyPassword;
    private String characterName;

    public UserDtoInput getUserDtoInput() {
        return userDtoInput;
    }

    public void setUserDtoInput(UserDtoInput userDtoInput) {
        this.userDtoInput = userDtoInput;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String toStringJson() {
        return new Gson().toJson(this);
    }
}
