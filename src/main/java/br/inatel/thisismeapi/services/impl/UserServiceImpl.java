package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.repositories.CharacterRepository;
import br.inatel.thisismeapi.repositories.UserRepository;
import br.inatel.thisismeapi.services.UserService;
import br.inatel.thisismeapi.services.exceptions.UnregisteredUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharacterRepository characterRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public User createNewAccount(User user, String characterName) {
        if (!(user.getPassword().length() >= 5 && user.getPassword().length() <= 30))
            throw new ConstraintViolationException("Senha deve conter no minimo 5 e no maximo 30 digitos!");

        user.setPassword(passwordEncoder().encode(user.getPassword()));

        Character character = characterRepository.save(new Character(characterName));

        user.setCharacter(character);

        return userRepository.save(user);
    }


    @Override
    public String login(User user) {
        Optional<User> opUser = userRepository.findByEmail(user.getEmail());

        if(opUser.isEmpty())
            throw new UnregisteredUserException("Nenhuma conta cadastrada com esse email!");


        if(!passwordEncoder().matches(user.getPassword(), opUser.get().getPassword()))
            throw new ConstraintViolationException("Senha incorreta!");

        return opUser.get().getId();
    }


}
