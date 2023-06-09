package com.perpetualtutorial.tutorialshare.template;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//@Primary
@NoRepositoryBean //NoRepositoryBean : indicate an interface to not be considered as a repository bean
public interface RepositoryTemplate<E> extends JpaRepository<E, Long> {
}
