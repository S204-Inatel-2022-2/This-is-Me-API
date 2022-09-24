package br.inatel.thisismeapi.utils;

import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;

public final class UserUtils {

    public static Boolean verifyPassword(String password, String verifyPassword) {

        if (!password.equals(verifyPassword))
            throw new ConstraintViolationException("As Senhas não coincidem!");

        return true;
    }
}
