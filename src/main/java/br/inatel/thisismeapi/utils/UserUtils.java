package br.inatel.thisismeapi.utils;

import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;

public final class UserUtils {

    public static Boolean verifyPassword(String password, String verifyPassword) {

        if (!password.equals(verifyPassword))
            throw new ConstraintViolationException("As Senhas não coincidem!");

        return true;
    }

    public static String verifyEmail(String email) {
        if (email == null)
            return "Email não pode ser nulo!";
        if (email.isBlank())
            return "Email não pode ser deixado em branco!";
        if (email.length() > 255)
            return "Email não pode ser ter mais de 255 digitos!";
        if (!email.matches(".+[@].+[\\\\.].+"))
            return "Email inválido!";

        return null;
    }
}
