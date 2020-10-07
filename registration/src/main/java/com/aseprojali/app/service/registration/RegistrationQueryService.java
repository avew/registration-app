package com.aseprojali.app.service.registration;

import com.aseprojali.app.domain.Registration;
import com.aseprojali.app.util.BaseNonTransactional;

import java.util.List;
import java.util.Optional;

public interface RegistrationQueryService extends BaseNonTransactional<Registration, String> {

    List<Registration> findAll();

    Optional<Registration> findByPhone(String phone);

    Optional<Registration> findByEmailIgnoreCase(String email);

}
