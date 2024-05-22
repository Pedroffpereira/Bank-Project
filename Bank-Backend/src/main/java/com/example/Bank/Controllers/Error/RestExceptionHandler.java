package com.example.Bank.Controllers.Error;

import java.util.HashMap;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for RESTful controllers.
 * 
 * @author Pedro Pereira
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handles MethodArgumentNotValidException and returns a response entity with
     * detailed validation errors.
     * 
     * @param ex      The MethodArgumentNotValidException instance.
     * @param headers The headers for the response entity.
     * @param status  The HTTP status code.
     * @param request The web request.
     * @return A response entity with detailed validation errors.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Handles UsernameNotFoundException, IllegalAccessError, and
     * AuthenticationException exceptions
     * and returns a response entity with an ApiError instance containing the error
     * details.
     * 
     * @param ex The exception instance.
     * @return A response entity with an ApiError instance containing the error
     *         details.
     */
    @ExceptionHandler({ IllegalAccessError.class })
    protected ResponseEntity<Object> handleEntityNotFound(IllegalAccessError ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({ UsernameNotFoundException.class })
    protected ResponseEntity<Object> handleEntityNotFound(UsernameNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({ AuthenticationException.class })
    protected ResponseEntity<Object> handleEntityNotFound(AuthenticationException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Builds a response entity with the given ApiError instance.
     * 
     * @param apiError The ApiError instance.
     * @return A response entity with the given ApiError instance.
     */
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
