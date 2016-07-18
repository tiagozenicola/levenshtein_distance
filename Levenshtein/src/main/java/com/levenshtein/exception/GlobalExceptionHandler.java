package com.levenshtein.exception;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler implements ErrorHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public void handleError(Throwable throwable) {
        logger.error(throwable);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data integrity violation")
    @ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class })
    public void handleException() {
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity<String> handleException(final Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
