package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.controllers.exceptions.PasswordVerifyIsNotEqualException;
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
    public User createNewAccount(String email, String password, String verifyPassword) {
        User user = new User(email, password);

        if (user.getPassword() != null && !user.getPassword().equals(verifyPassword))
            throw new PasswordVerifyIsNotEqualException("As Senhas não coincidem!");

        return userRepository.save(user);
    }
}
