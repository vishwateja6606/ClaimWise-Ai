package com.claimwise.auth.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExist(EmailAlreadyExistsException ex, HttpServletRequest request){
        return builderResponse(ex.getMessage(), HttpStatus.CONFLICT,request);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPassword(InvalidPasswordException ex,HttpServletRequest request){
        return builderResponse(ex.getMessage(),HttpStatus.CONFLICT,request);
    }

    private ResponseEntity<ErrorResponse> builderResponse(String message,HttpStatus status,HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()

        );
        return new ResponseEntity<>(response,status);
    }
}
