package com.perpetualtutorial.tutorialshare.Controllers.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityNotFoundAdvice {
    @ResponseBody                                       // ResponseBody: signals advice to be rendered straight into response body
    @ExceptionHandler(EntityNotFoundException.class)    // Exceptionhandler(excpt): config advice to only response if excpt is thrown
    @ResponseStatus(HttpStatus.NOT_FOUND)               // ResponseStatus(status): says to issue specified status
    String recipeNotFoundHandler(EntityNotFoundException excpt) {
        return excpt.getMessage();
    }
}

