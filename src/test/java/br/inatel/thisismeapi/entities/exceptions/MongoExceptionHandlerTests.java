package br.inatel.thisismeapi.entities.exceptions;

import br.inatel.thisismeapi.config.exceptions.StandardError;
import br.inatel.thisismeapi.entities.Exceptions.MongoExceptionHandler;
import com.mongodb.MongoWriteException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteError;
import org.bson.BsonDocument;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MongoExceptionHandlerTests {
    @Autowired
    MongoExceptionHandler mongoExceptionHandler;

    @Mock
    HttpServletRequest httpServletRequest;

    @Test
    public void testWriteErrorE11000 () {
        WriteError writeError = new WriteError(11000, "code=11000, message=", new BsonDocument());
        MongoWriteException mongoWriteException = new MongoWriteException(writeError, new ServerAddress());

        ResponseEntity<StandardError> responseEntity =  mongoExceptionHandler
                .mongoWrite(mongoWriteException, httpServletRequest);

        assertEquals("Já Existe uma conta cadastrada com esse e-mail!",
                Objects.requireNonNull(responseEntity.getBody()).getMessage());
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals(HttpStatus.CONFLICT.getReasonPhrase(), responseEntity.getBody().getError());
        assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getBody().getStatus());

    }
}
