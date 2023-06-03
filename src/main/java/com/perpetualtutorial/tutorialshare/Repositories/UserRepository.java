package com.perpetualtutorial.tutorialshare.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpetualtutorial.tutorialshare.Models.User.*;
public interface UserRepository extends JpaRepository<User, Long> {
}
