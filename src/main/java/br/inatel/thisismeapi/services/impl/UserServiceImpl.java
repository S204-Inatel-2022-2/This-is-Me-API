package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.repositories.UserRepository;
import br.inatel.thisismeapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createNewAccount(String email, String password) throws ConstraintViolationException {
        User user = new User(email, password);
        userRepository.save(user);
    }
}
