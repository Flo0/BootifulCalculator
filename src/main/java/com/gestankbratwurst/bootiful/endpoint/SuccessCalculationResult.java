package com.gestankbratwurst.bootiful.endpoint;

import javax.ws.rs.core.Response;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 05.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class SuccessCalculationResult<T extends Number> implements CalculationResult {

  private final T result;

  public SuccessCalculationResult(T result) {
    this.result = result;
  }
  
  @Override
  public Response getAsDecoratedResponse() {
    return Response.ok(this).build();
  }
}