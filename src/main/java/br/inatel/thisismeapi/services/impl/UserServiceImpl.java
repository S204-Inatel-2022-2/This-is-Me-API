package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.Roles;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.enums.RoleName;
import br.inatel.thisismeapi.repositories.CharacterRepository;
import br.inatel.thisismeapi.repositories.UserRepository;
import br.inatel.thisismeapi.services.UserService;
import br.inatel.thisismeapi.services.exceptions.TokenInvalidException;
import br.inatel.thisismeapi.services.exceptions.UnregisteredUserException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    @Value("${private.key.mail}")
    private String PRIVATE_KEY_MAIL;

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

    @Override
    public void resetPassword(String password, String jwt) {
        if (!(password.length() >= 5 && password.length() <= 30))
            throw new ConstraintViolationException("Senha deve conter no minimo 5 e no maximo 30 digitos!");

        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(PRIVATE_KEY_MAIL))
                    .build()
                    .verify(jwt);

            String email = decodedJWT.getClaim("email").asString();
            Optional<User> userOp = userRepository.findByEmail(email);

            if (userOp.isEmpty()) {
                LOGGER.info("m=resetPassword, msg=Usuario não encontrado");
                throw new UnregisteredUserException("Usuario não encontrado!");
            }


            User user = userOp.get();

            // TODO: verificar se tem como alterar o token quando finalizar a troca de senha para invalidalo
            if (user.getTokenResetPassword() == null) {
                throw new TokenInvalidException("Token já utilizado, por favor gere um novo codigo para a alteração de senha.");
            }

            user.setPassword(password);
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            user.setTokenResetPassword(null);
            userRepository.save(user);
            LOGGER.info("m=verifyNumberPassword, status=senha alterada com sucesso");
        } catch (JWTVerificationException e) {
            LOGGER.info("m=verifyNumberPassword, errorMsg={}", e.getMessage());
            throw new TokenInvalidException(e.getMessage());
        }
    }
}
