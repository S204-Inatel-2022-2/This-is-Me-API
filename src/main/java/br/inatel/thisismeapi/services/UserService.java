package br.inatel.thisismeapi.services;

import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.User;

public interface UserService {

    User createNewAccount(User user, Character character);

    Character findCharacterByEmail(String email);

    void resetPassword(String password, String jwt);
}
