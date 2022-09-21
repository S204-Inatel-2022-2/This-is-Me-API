package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.Roles;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.enums.RoleName;
import br.inatel.thisismeapi.repositories.CharacterRepository;
import br.inatel.thisismeapi.repositories.UserRepository;
import br.inatel.thisismeapi.services.UserService;
import br.inatel.thisismeapi.services.exceptions.UnregisteredUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharacterRepository characterRepository;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public User createNewAccount(User user, String characterName) {

        LOGGER.info("m=createNewAccount, type=User , email={}, characterName={}", user.getEmail(), characterName);
        if (!(user.getPassword().length() >= 5 && user.getPassword().length() <= 30))
            throw new ConstraintViolationException("Senha deve conter no minimo 5 e no maximo 30 digitos!");

        user.setPassword(passwordEncoder().encode(user.getPassword()));

        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(RoleName.ROLE_USER));
        user.setRoles(roles);

        Character character = characterRepository.save(new Character(characterName));
        user.setCharacter(character);

        return userRepository.save(user);
    }

    @Override
    public Character findCharacterByEmail(String email) {
        LOGGER.info("m=findCharacterByEmail, email={}", email);
        Optional<User> opUser = userRepository.findByEmail(email);

        if (opUser.isEmpty())
            throw new UnregisteredUserException("Nenhuma conta cadastrada com esse email!");

        LOGGER.info("m=findCharacterByEmail, email={}, characterName={}",
                email, opUser.get().getCharacter().getCharacterName());
        return opUser.get().getCharacter();
    }

}
