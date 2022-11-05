package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.enums.RoleName;
import br.inatel.thisismeapi.exceptions.UnregisteredUserException;
import br.inatel.thisismeapi.exceptions.mongo.UniqueViolationConstraintException;
import br.inatel.thisismeapi.models.Roles;
import br.inatel.thisismeapi.repositories.UserRepository;
import br.inatel.thisismeapi.services.CharacterService;
import br.inatel.thisismeapi.services.UserService;
import br.inatel.thisismeapi.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharacterService characterService;

    @Override
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public User saveNewAccount(String email, String password, String verifyPassword, String characterName) {

        LOGGER.info("m=saveNewAccount, type=Admin, email={}, characterName={}", email, characterName);
        UserUtils.verifyEmail(email);
        UserUtils.verifyPassword(password, verifyPassword);
        UserUtils.verifyDecryptedPasswordLength(password);

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder().encode(password));
        user.setRoles(this.getAdminRoles());

        Character character = characterService.saveNewCharacter(new Character(email, characterName));
        user.setCharacter(character);
        try {
            return userRepository.save(user);
        } catch (DuplicateKeyException e) {
            throw new UniqueViolationConstraintException("Já Existe uma conta cadastrada com esse e-mail!");
        }
    }

    @Override
    public User updateUser(User user) {

        LOGGER.info("m=updateUser, type=Admin, email={}, characterName={}", user.getEmail(), user.getCharacter().getCharacterName());
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {

        LOGGER.info("m=findUserByEmail, type=Admin, email={}", email);
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UnregisteredUserException("Usuário com email [" + email + "] não encontrado!");
        }

        return userOptional.get();
    }

    // TODO: Implementar

    @Override
    public void sendEmailToResetPassword(String email) {

    }

    @Override
    public String getResetTokenWithEmailAndNumber(String email, Integer number) {
        return null;
    }

    @Override
    public void resetPassword(String password, String passwordVerify, String resetToken) {

    }


    private List<Roles> getAdminRoles() {
        List<Roles> roles = new ArrayList<>();
        roles.add(new Roles(RoleName.ROLE_USER));
        roles.add(new Roles(RoleName.ROLE_ADMIN));
        return roles;
    }
}
