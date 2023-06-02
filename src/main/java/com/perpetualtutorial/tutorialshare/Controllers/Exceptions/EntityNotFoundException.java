package com.perpetualtutorial.tutorialshare.Controllers.Exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("No data model found with matching id: " + id);
    }
}
