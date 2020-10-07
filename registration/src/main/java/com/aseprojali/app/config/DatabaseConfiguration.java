package com.aseprojali.app.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;

/*
 * Developed by Asep Rojali on 12/18/18 7:25 PM
 * Last modified 12/18/18 6:00 PM
 * Copyright (c) 2018. All rights reserved.
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.aseprojali.app.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware", dateTimeProviderRef = "utcDateTimeProvider")
@EnableTransactionManagement
@Slf4j
public class DatabaseConfiguration {

    @Bean
    public DateTimeProvider utcDateTimeProvider() {
        return () -> Optional.of(Instant.now().atZone(ZoneOffset.of("+07:00")));
    }
}