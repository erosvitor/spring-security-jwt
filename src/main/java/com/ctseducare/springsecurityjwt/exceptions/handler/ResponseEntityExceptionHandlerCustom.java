package com.ctseducare.springsecurityjwt.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ctseducare.springsecurityjwt.exceptions.ExceptionResponse;
import com.ctseducare.springsecurityjwt.exceptions.InvalidJwtAuthenticationException;

@ControllerAdvice
@RestController
public class ResponseEntityExceptionHandlerCustom extends ResponseEntityExceptionHandler{
  
  @ExceptionHandler(InvalidJwtAuthenticationException.class)
  public final ResponseEntity<ExceptionResponse> invalidJwtAuthenticationException(Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

}