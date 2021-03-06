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
public class ErrorResult implements CalculationResult {

  private final String error;

  public ErrorResult(final String errorMessage) {
    this.error = errorMessage;
  }
  
  @Override
  public Response getAsDecoratedResponse() {
    return Response.status(400).entity(this).build();
  }
}
