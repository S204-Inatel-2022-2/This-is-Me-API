package br.inatel.thisismeapi.services.exceptions;

import br.inatel.thisismeapi.config.exceptions.StandardError;
import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ServicesExceptionsHandler {
    @ExceptionHandler(UnregisteredUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<StandardError> unregisteredUserException(UnregisteredUserException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
