package com.aseprojali.app.exception;

/*
 * Developed by Asep Rojali on 12/19/18 4:07 PM
 * Last modified 12/19/18 4:07 PM
 * Copyright (c) 2018. All rights reserved.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import javax.validation.ConstraintViolationException;
import java.net.URI;

import static com.aseprojali.app.exception.ErrorConstants.CONSTRAINT_VIOLATION_TYPE;
import static com.aseprojali.app.exception.ErrorConstants.NOT_FOUND_TYPE;


@ControllerAdvice
@Slf4j
public class ExceptionHandling implements ProblemHandling {

    @Override
    public URI defaultConstraintViolationType() {
        return CONSTRAINT_VIOLATION_TYPE;
    }

    @Override
    public boolean isCausalChainsEnabled() {
        return false;
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleConstraintViolated(final DataIntegrityViolationException exception,
                                                            final NativeWebRequest request) {
        if (exception.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            org.hibernate.exception.ConstraintViolationException constraintViolationException =
                    (org.hibernate.exception.ConstraintViolationException) exception.getCause();
            return create(Status.CONFLICT, constraintViolationException.getSQLException(), request);
        }
        return create(Status.INTERNAL_SERVER_ERROR, exception, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleEmptyResult(final EmptyResultDataAccessException exception,
                                                     final NativeWebRequest request) {

        return create(Status.NOT_FOUND, exception, request, NOT_FOUND_TYPE);
    }

    //EmptyResultDataAccessException

    @SuppressWarnings("PlaceholderCountMatchesArgumentCount")
    @ExceptionHandler
    public ResponseEntity<Problem> handleNestedConstrainException(final TransactionException exception, final NativeWebRequest request) {
        Throwable rootCause = exception.getRootCause();
        if ((!(rootCause instanceof ConstraintViolationException))) {
            log.debug("Found TransactionException, but  rootCause is {}, using default handling", rootCause);
            return create(exception, request);
        }
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) rootCause;
        return handleConstraintViolation(constraintViolationException, request);
    }
}
