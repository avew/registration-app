package com.aseprojali.app.service.registration.impl;

import com.aseprojali.app.domain.Registration;
import com.aseprojali.app.repository.RegistrationRepository;
import com.aseprojali.app.service.registration.RegistrationQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationQueryServiceImpl implements RegistrationQueryService {
    private final RegistrationRepository registrationRepository;

    @Override
    public Optional<Registration> findById(String s) {
        return registrationRepository.findById(s);
    }

    @Override
    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    @Override
    public Optional<Registration> findByPhone(String phone) {
        return registrationRepository.findByPhone(phone);
    }

    @Override
    public Optional<Registration> findByEmailIgnoreCase(String email) {
        return registrationRepository.findByEmailIgnoreCase(email);
    }
}
