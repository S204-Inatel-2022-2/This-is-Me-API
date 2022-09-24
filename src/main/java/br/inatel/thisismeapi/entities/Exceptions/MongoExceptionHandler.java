package br.inatel.thisismeapi.entities.Exceptions;

import br.inatel.thisismeapi.config.exceptions.StandardError;
import com.mongodb.MongoWriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class MongoExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoExceptionHandler.class);

    @ExceptionHandler(MongoWriteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<StandardError> mongoWrite(MongoWriteException e, HttpServletRequest request) {
        LOGGER.info("m=mongoWrite, statusCode={}, msg={}", HttpStatus.BAD_REQUEST.value(), e.getMessage());
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError(HttpStatus.CONFLICT.getReasonPhrase());
        error.setMessage(this.getMsgByMongoErrorMessage(e.getMessage()));
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    private String getMsgByMongoErrorMessage(String msg) {
        int start = msg.indexOf("code=") + 5;
        msg = msg.substring(start);
        int end = msg.indexOf(", message=");
        return MongoErrorMsgConst.getMsgByCode("E" + msg.substring(0, end));
    }
}
