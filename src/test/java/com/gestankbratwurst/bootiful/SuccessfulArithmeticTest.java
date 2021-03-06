package com.gestankbratwurst.bootiful;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestankbratwurst.bootiful.calculation.ArithmeticOperation;
import com.gestankbratwurst.bootiful.calculation.NumberType;
import com.gestankbratwurst.bootiful.endpoint.SuccessCalculationResult;
import io.restassured.http.ContentType;
import java.math.BigDecimal;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 06.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class SuccessfulArithmeticTest extends CalculationResourceTest {

  protected void decimal(final ArithmeticOperation operation, final double result, final Number... values)
      throws JsonProcessingException {
    given().body(this.objectMapper.writeValueAsString(payload(operation, NumberType.DECIMAL, values)))
        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
        .when().post("/calculate")
        .then()
        .statusCode(200)
        .body(is(this.objectMapper.writeValueAsString(new SuccessCalculationResult<>(result))));
  }

  protected void integer(final ArithmeticOperation operation, final int result, final Number... values)
      throws JsonProcessingException {
    given().body(this.objectMapper.writeValueAsString(payload(operation, NumberType.INTEGER, values)))
        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
        .when().post("/calculate")
        .then()
        .statusCode(200)
        .body(is(this.objectMapper.writeValueAsString(new SuccessCalculationResult<>(result))));
  }

  protected void safe(final ArithmeticOperation operation, final BigDecimal result, final Number... values)
      throws JsonProcessingException {
    given().body(this.objectMapper.writeValueAsString(payload(operation, NumberType.SAFE, values)))
        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
        .when().post("/calculate")
        .then()
        .statusCode(200)
        .body(is(this.objectMapper.writeValueAsString(new SuccessCalculationResult<>(result))));
  }

}
