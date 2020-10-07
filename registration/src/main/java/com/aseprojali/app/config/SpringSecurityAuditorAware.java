package com.aseprojali.app.config;


import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    private String createdBy;

    public String setCreatedBy(String createdBy) {
        return this.createdBy = createdBy;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        if (createdBy != null)
            return Optional.of(createdBy);
        else
            return Optional.of(ApplicationConstant.SYSTEM_ACCOUNT);
    }

}
