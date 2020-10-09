package com.aseprojali.app.service.registration.impl;

import com.aseprojali.app.domain.Registration;
import com.aseprojali.app.exception.ExistsException;
import com.aseprojali.app.repository.RegistrationRepository;
import com.aseprojali.app.service.registration.RegistrationQueryService;
import com.aseprojali.app.service.registration.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationQueryService registrationQueryService;

    @Override
    public Registration save(Registration dto) {
        log.debug("REQ SAVE: {}", dto);

        if (registrationQueryService.findByPhone(dto.getPhone()).isPresent())
            throw new ExistsException(String.format("Phone %s already registered", dto.getPhone()));
        if (registrationQueryService.findByEmailIgnoreCase(dto.getEmail()).isPresent())
            throw new ExistsException(String.format("Email %s already registered", dto.getEmail()));

        return registrationRepository.save(dto);
    }

    @Override
    public Registration update(Registration dto) {
        log.debug("REQ UPDATE: {}", dto);
        return null;
    }

    @Override
    public void deleteById(String s) {

    }
}
