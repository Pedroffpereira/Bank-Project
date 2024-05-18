package com.example.Bank.Controllers.Error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Represents an error response in the API.
 * 
 * @author Pedro Pereira
 */
@Data
public class ApiError {
    /** The HTTP status of the error response. */
    private HttpStatus status;

    /** The timestamp when the error occurred. */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    /** A brief message describing the error. */
    private String message;

    /** A detailed message or stack trace of the error, useful for debugging. */
    private String debugMessage;

    /**
     * Initializes the timestamp to the current date and time.
     */
    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    /**
     * Initializes the timestamp and sets the HTTP status.
     * 
     * @param status The HTTP status of the error response.
     */
    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    /**
     * Initializes the timestamp, sets the HTTP status to indicate an unexpected
     * error,
     * and sets the debugMessage to the localized message of the provided Throwable.
     * 
     * @param status The HTTP status of the error response.
     * @param ex     The Throwable representing the exception.
     */
    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Initializes the timestamp, sets the HTTP status, and sets custom message and
     * debugMessage.
     * 
     * @param status  The HTTP status of the error response.
     * @param message A brief message describing the error.
     * @param ex      The Throwable representing the exception.
     */
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

}
