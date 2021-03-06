package com.gestankbratwurst.bootiful;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestankbratwurst.bootiful.calculation.ArithmeticOperation;
import com.gestankbratwurst.bootiful.calculation.NumberType;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 06.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
@QuarkusTest
public class ErrorCalculatingTest extends CalculationResourceTest {

  private void faulty(final String operation, final String type, final Number... values)
      throws JsonProcessingException {
    given().body(this.objectMapper.writeValueAsString(payload(operation, type, values)))
        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
        .when().post("/calculate")
        .then()
        .statusCode(400);
  }

  @Test
  public void missingValueTest() throws JsonProcessingException {
    for (final ArithmeticOperation operation : ArithmeticOperation.values()) {
      for (final NumberType type : NumberType.values()) {
        this.faulty(operation.getIdentifier(), type.getIdentifier());
        this.faulty(operation.getIdentifier(), type.getIdentifier(), (Number[]) null);
      }
    }
  }

  @Test
  public void missingTypeTest() throws JsonProcessingException {
    for (final ArithmeticOperation operation : ArithmeticOperation.values()) {
      this.faulty(operation.getIdentifier(), null, 1, 2, 3, 4);
    }
  }

  @Test
  public void wrongTypeTest() throws JsonProcessingException {
    for (final ArithmeticOperation operation : ArithmeticOperation.values()) {
      this.faulty(operation.getIdentifier(), "baz", 1, 2, 3, 4);
    }
  }

  @Test
  public void missingOperationTest() throws JsonProcessingException {
    for (final NumberType type : NumberType.values()) {
      this.faulty(null, type.getIdentifier(), 1);
    }
  }

  @Test
  public void wrongOperationTest() throws JsonProcessingException {
    for (final NumberType type : NumberType.values()) {
      this.faulty("baz", type.getIdentifier(), 1);
    }
  }

  @Test
  public void unrepresentableValue() throws JsonProcessingException {
    this.faulty(ArithmeticOperation.DIVIDE.getIdentifier(), NumberType.SAFE.getIdentifier(), 90, 1.11);
  }

  @Test
  public void divisionByZero() throws JsonProcessingException {
    this.faulty(ArithmeticOperation.DIVIDE.getIdentifier(), NumberType.INTEGER.getIdentifier(), 90, 0);
  }

}
