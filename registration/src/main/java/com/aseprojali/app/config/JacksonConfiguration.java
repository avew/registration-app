package com.aseprojali.app.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;

/*
 * Developed by Asep Rojali on 12/19/18 10:32 AM
 * Last modified 10/12/18 10:20 AM
 * Copyright (c) 2018. All rights reserved.
 */

@Configuration
public class JacksonConfiguration {

    /*
     * Module for serialization/deserialization of RFC7807 Problem.
     */
    @Bean
    public ProblemModule problemModule() {
        return new ProblemModule().withStackTraces(false);
    }

    @Bean
    public Module dateTimeModule() {
        return new JavaTimeModule();
    }
}
