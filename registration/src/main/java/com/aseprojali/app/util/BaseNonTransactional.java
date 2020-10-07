package com.aseprojali.app.util;

/*
 * Developed by Asep Rojali on 12/20/18 2:05 PM
 * Last modified 12/20/18 1:31 PM
 * Copyright (c) 2018. All rights reserved.
 */


import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface BaseNonTransactional<D, ID> {


    Optional<D> findById(ID id);


}
