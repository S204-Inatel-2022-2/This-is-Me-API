package br.inatel.thisismeapi.services;

import br.inatel.thisismeapi.entities.User;

public interface UserService {

    User createNewAccount(User user, String characterName);

    User login(User user);
}
