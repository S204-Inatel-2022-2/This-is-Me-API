package br.inatel.thisismeapi.services.exceptions;

import br.inatel.thisismeapi.config.exceptions.StandardError;
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
public class ServicesExceptionsHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicesExceptionsHandler.class);

    @ExceptionHandler(UnregisteredUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<StandardError> unregisteredUserException(UnregisteredUserException e, HttpServletRequest request) {

        LOGGER.error("m=unregisteredUserException, statusCode={}, msg={}",
                HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler({TokenExpiredException.class, TokenInvalidException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<StandardError> tokenHandler(RuntimeException e, HttpServletRequest request) {

        LOGGER.error("m=tokenHandler, statusCode={}, msg={}",
                HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
