package com.aseprojali.app.web.rest;

import com.aseprojali.app.config.ApiConstant;
import com.aseprojali.app.domain.Registration;
import com.aseprojali.app.service.mapper.RegistrationMapper;
import com.aseprojali.app.service.registration.RegistrationService;
import com.aseprojali.app.web.dto.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiConstant.ROOT + ApiConstant.V1 + ApiConstant.REGISTRATION.ROOT)
@RequiredArgsConstructor
@Slf4j
public class RegistrationRestController {

    private final RegistrationService registrationService;
    private final RegistrationMapper registrationMapper;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody RegistrationDTO dto) {
        Registration registration = registrationMapper.toEntity(dto);
        Registration saved = registrationService.save(registration);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
