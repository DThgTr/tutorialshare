package com.perpetualtutorial.tutorialshare.feature.User;


import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}
