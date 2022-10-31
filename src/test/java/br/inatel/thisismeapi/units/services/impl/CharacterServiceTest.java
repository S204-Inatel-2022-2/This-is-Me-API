package br.inatel.thisismeapi.units.services.impl;


import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.exceptions.ErrorOnCreateException;
import br.inatel.thisismeapi.exceptions.TokenInvalidException;
import br.inatel.thisismeapi.exceptions.UnregisteredUserException;
import br.inatel.thisismeapi.exceptions.mongo.UniqueViolationConstraintException;
import br.inatel.thisismeapi.repositories.CharacterRepository;
import br.inatel.thisismeapi.repositories.UserRepository;
import br.inatel.thisismeapi.services.CharacterService;
import br.inatel.thisismeapi.services.MailService;
import br.inatel.thisismeapi.services.impl.CharacterServiceImpl;
import br.inatel.thisismeapi.services.impl.UserServiceImpl;
import br.inatel.thisismeapi.units.classestotest.EmailConstToTest;
import br.inatel.thisismeapi.units.classestotest.PasswordConstToTest;
import br.inatel.thisismeapi.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CharacterServiceImpl.class})
@ActiveProfiles("test")
class CharacterServiceTest {

    @Autowired
    private CharacterServiceImpl characterService;

    @MockBean
    private CharacterRepository characterRepository;


    @Test
    void testFindCharacterByEmailSuccess() {

        String email = EmailConstToTest.EMAIL_DEFAULT;
        Character character = new Character();
        character.setEmail(email);

        when(characterRepository.findCharacterByEmail(email)).thenReturn(Optional.of(character));
        Character actualChar = this.characterService.findCharacterByEmail(email);

        assertEquals(character, actualChar);
        assertEquals(character.getEmail(), actualChar.getEmail());
    }

    @Test
    void testFindCharacterByEmailThrowExceptionWhenNotFoundUser() {

        String email = EmailConstToTest.EMAIL_DEFAULT;
        String expectedMessage = "Personagem com email [" + email + "] não encontrado!";

        when(characterRepository.findCharacterByEmail(email)).thenReturn(Optional.empty());
        UnregisteredUserException exception = assertThrows(UnregisteredUserException.class, ()->{
            this.characterService.findCharacterByEmail(email);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testSaveNewCharacterSuccess() {

        String email = EmailConstToTest.EMAIL_DEFAULT;
        Character character = new Character();
        character.setEmail(email);

        when(characterRepository.save(character)).thenReturn(character);
        Character actualChar = this.characterService.saveNewCharacter(character);

        assertEquals(character, actualChar);
        assertEquals(character.getEmail(), actualChar.getEmail());
    }

    @Test
    void testSaveNewCharacterThrowExceptionWhenAlreadyExistCharacterInAccount() {

        String email = EmailConstToTest.EMAIL_DEFAULT;
        Character character = new Character();
        character.setEmail(email);
        String expectedMessage = "Já Existe uma conta cadastrada com esse e-mail!";

        when(characterRepository.save(character)).thenThrow(DuplicateKeyException.class);
        UniqueViolationConstraintException exception = assertThrows(UniqueViolationConstraintException.class, ()->{
            this.characterService.saveNewCharacter(character);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testUpdateCharacterSuccess() {

        String email = EmailConstToTest.EMAIL_DEFAULT;
        Character character = new Character();
        character.setEmail(email);
        character.setId("123456");
        when(characterRepository.save(character)).thenReturn(character);
        Character actualChar = this.characterService.updateCharacter(character);

        assertEquals(character, actualChar);
        assertEquals(character.getEmail(), actualChar.getEmail());
    }

    @Test
    void testSaveUpdateCharacterThrowExceptionWhenCharacterDoNotHaveIdToUpdate() {

        String email = EmailConstToTest.EMAIL_DEFAULT;
        Character character = new Character();
        character.setEmail(email);
        String expectedMessage = "Necessário que o usuário tenha um personagem para utilizá-lo!";

        UnregisteredUserException exception = assertThrows(UnregisteredUserException.class, ()->{
            this.characterService.updateCharacter(character);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testSetClothesSuccess() {

        String email = EmailConstToTest.EMAIL_DEFAULT;
        Character character = new Character();
        character.setEmail(email);
        character.setClothes(1L);

        when(characterRepository.findCharacterByEmail(email)).thenReturn(Optional.of(character));
        when(characterRepository.save(character)).thenReturn(character);
        Character actualChar = this.characterService.setClothes(email, 10L);

        assertEquals(character, actualChar);
        assertEquals(character.getEmail(), actualChar.getEmail());
        assertEquals(10L, actualChar.getClothes());
    }
}
