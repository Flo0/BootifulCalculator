package com.gestankbratwurst.bootiful.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 05.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
@Singleton
public class ObjectMapperProvider implements Provider<ObjectMapper> {

  @Inject
  FieldEnabledObjectMapperCustomizer customizer;

  @Override
  public ObjectMapper get() {
    ObjectMapper mapper = new ObjectMapper();
    customizer.customize(mapper);
    return mapper;
  }

}
