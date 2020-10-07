package com.aseprojali.app.service.mapper;

import com.aseprojali.app.domain.Registration;
import com.aseprojali.app.web.dto.RegistrationDTO;

public interface RegistrationMapper {

    Registration toEntity(RegistrationDTO registrationDTO);
}
