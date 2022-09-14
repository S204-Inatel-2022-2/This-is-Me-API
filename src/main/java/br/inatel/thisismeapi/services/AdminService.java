package br.inatel.thisismeapi.services;

import br.inatel.thisismeapi.entities.User;
import org.springframework.stereotype.Service;

public interface AdminService {
    User createNewAccount(User user, String characterName);
}
