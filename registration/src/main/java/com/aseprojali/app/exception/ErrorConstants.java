package com.aseprojali.app.exception;

import java.net.URI;

@SuppressWarnings("SpellCheckingInspection")
public class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://api.app.com/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/not-found");
    public static final URI PHONE_EXISTS_TYPE = URI.create(PROBLEM_BASE_URL + "/phone-exists");
    public static final URI EMAIL_EXISTS_TYPE = URI.create(PROBLEM_BASE_URL + "/email-exists");

    private ErrorConstants() {
    }
}
