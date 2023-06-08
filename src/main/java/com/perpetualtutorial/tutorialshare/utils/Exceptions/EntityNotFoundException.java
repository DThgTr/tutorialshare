package com.perpetualtutorial.tutorialshare.utils.Exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("No data model found with matching id: " + id);
    }
}
