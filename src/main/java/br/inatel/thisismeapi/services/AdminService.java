package br.inatel.thisismeapi.services;

import br.inatel.thisismeapi.entities.User;

public interface AdminService {
    User createNewAccount(User user, String characterName);
}
