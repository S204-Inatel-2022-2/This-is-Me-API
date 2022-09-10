package br.inatel.thisismeapi.entities;

import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document
public class User {

    @Id
    private String id;
    @Indexed(name = "unique_email", unique = true)
    @NotNull()
    @NotBlank()
    @Email(regexp = ".+[@].+[\\\\.].+")
    @Size(max = 255)
    private String email;

    @NotNull()
    @NotBlank()
    private String password;

    public User() {
    }

    public User(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null)
            throw new ConstraintViolationException("Email não pode ser nulo!");
        if (email.isBlank())
            throw new ConstraintViolationException("Email não pode ser deixado em branco!");
        if (email.length() > 255)
            throw new ConstraintViolationException("Email não pode ser ter mais de 255 digitos!");
        if (!email.matches(".+[@].+[\\\\.].+"))
            throw new ConstraintViolationException("Email inválido!");

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null)
            throw new ConstraintViolationException("Senha não pode ser nula!");
        if (password.isBlank())
            throw new ConstraintViolationException("Senha não pode ser deixada em branco!");

        this.password = password;
    }

    public void verifyPassword(String verifyPassword) {
        if (!password.equals(verifyPassword))
            throw new ConstraintViolationException("As Senhas não coincidem!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);
    }
}
