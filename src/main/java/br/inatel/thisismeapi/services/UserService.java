package br.inatel.thisismeapi.services;

import br.inatel.thisismeapi.entities.User;

public interface UserService {

    User createNewAccount(String email, String password, String verifyPassword);

}
