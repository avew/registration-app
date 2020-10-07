package com.aseprojali.app.service.mapper.impl;

import com.aseprojali.app.domain.Registration;
import com.aseprojali.app.service.mapper.RegistrationMapper;
import com.aseprojali.app.web.dto.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public Registration toEntity(RegistrationDTO dto) {
        LocalDate dob = null;
        Registration registration = Registration.builder()
                .phone(dto.getPhone())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .gender(dto.getGender())
                .email(dto.getEmail().toLowerCase())
                .build();
        try {
            dob = LocalDate.of(dto.getYear(), dto.getMonth(), dto.getDate());
        } catch (DateTimeException ignore) {
        }
        registration.setDob(Optional.ofNullable(dob).orElse(null));

        return registration;

    }
}
