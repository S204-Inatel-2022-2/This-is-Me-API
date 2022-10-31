package br.inatel.thisismeapi.services;

import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface AdminService {

    User saveNewAccount(String email, String password, String verifyPassword, String characterName);

    User updateUser(User user);

    User findUserByEmail(String email);

    Character findCharacterByEmail(String email);

    void sendEmailToResetPassword(String email);

    String verifyNumberOfResetTokenPassword(String email, Integer number);

    public void resetPassword(String password, String passwordVerify, String resetToken);

    BCryptPasswordEncoder passwordEncoder();

}
