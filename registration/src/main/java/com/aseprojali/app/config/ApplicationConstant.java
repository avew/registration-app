package com.aseprojali.app.config;

/*
 * Developed by Asep Rojali on 12/18/18 7:25 PM
 * Last modified 12/18/18 5:58 PM
 * Copyright (c) 2018. All rights reserved.
 */

public final class ApplicationConstant {

    // Spring profiles for development, test and production,
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    // Spring profile used when deploying with Spring Cloud (used when deploying to CloudFoundry)
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    // Spring profile used when deploying to Heroku
    public static final String SPRING_PROFILE_HEROKU = "heroku";
    // Spring profile used to disable swagger
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    // Spring profile used when deploying to Kubernetes and OpenShift
    public static final String SPRING_PROFILE_K8S = "k8s";
    public static final String SEPARATOR = "/";
    public static final String SYSTEM_ACCOUNT = "system";

}
