package br.inatel.thisismeapi.entities;

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
    @Size(min = 5, max = 50)
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
