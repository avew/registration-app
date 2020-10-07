package com.aseprojali.app.exception;

/*
 * Developed by Asep Rojali on 12/21/18 4:08 PM
 * Last modified 12/19/18 5:02 PM
 * Copyright (c) 2018. All rights reserved.
 */


import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.concurrent.Immutable;

import static com.aseprojali.app.exception.ErrorConstants.EMAIL_EXISTS_TYPE;

@Immutable
public class EmailExistsException extends AbstractThrowableProblem {

    public EmailExistsException(String message) {
        super(EMAIL_EXISTS_TYPE, "Warning", Status.CONFLICT, message);

    }

}
