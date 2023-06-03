package com.perpetualtutorial.tutorialshare.Models;

import org.springframework.stereotype.Component;

@Component
public interface EntityServices<E> {
    Long getId();
    void setId(Long id);
    void update(E updatedEntity);
}
