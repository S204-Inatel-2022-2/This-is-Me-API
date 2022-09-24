package br.inatel.thisismeapi.services;

public interface MailService {

    void sendEmailForgotPassword(String email);

    String verifyNumberPassword(String email, String number);
}
