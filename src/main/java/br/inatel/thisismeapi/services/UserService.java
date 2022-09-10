package br.inatel.thisismeapi.services;

import br.inatel.thisismeapi.entities.User;

public interface UserService {

    User createNewAccount(User user);

    //User login(User user);
}
