package com.aseprojali.app.repository;

import com.aseprojali.app.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {

    Optional<Registration> findByPhone(String phone);

    Optional<Registration> findByEmailIgnoreCase(String email);
}
