package com.gestankbratwurst.bootiful;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestankbratwurst.bootiful.calculation.ArithmeticOperation;
import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
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
public class SuccessfulPayloadTest extends SuccessfulArithmeticTest {

  @Test
  public void additionDecimalTest() throws JsonProcessingException {
    this.decimal(ArithmeticOperation.ADD, (20.2 + 20.3 + 20.4 + 20.5 + 20.6), 20.2, 20.3, 20.4, 20.5, 20.6);
  }

  @Test
  public void subtractionDecimalTest() throws JsonProcessingException {
    this.decimal(ArithmeticOperation.SUBTRACT, (13.5 - 11.2 - 11.2), 13.5, 11.2, 11.2);
  }

  @Test
  public void multiplyDecimalTest() throws JsonProcessingException {
    this.decimal(ArithmeticOperation.MULTIPLY, (4.5 * 5.5 * 8.5), 4.5, 5.5, 8.5);
  }

  @Test
  public void divideDecimalTest() throws JsonProcessingException {
    this.decimal(ArithmeticOperation.DIVIDE, (9000 / (1.7 * 3.2)), 9000.0, 1.7, 3.2);
  }

  @Test
  public void additionIntegerTest() throws JsonProcessingException {
    this.integer(ArithmeticOperation.ADD, (20 + 30 + 50 + 90 + 702), 20, 30, 50, 90, 702);
  }

  @Test
  public void subtractionIntegerTest() throws JsonProcessingException {
    this.integer(ArithmeticOperation.SUBTRACT, (402 - 30 - 77 - 90 - 702), 402, 30, 77, 90, 702);
  }

  @Test
  public void multiplyIntegerTest() throws JsonProcessingException {
    this.integer(ArithmeticOperation.MULTIPLY, (20 * 30 * 50), 20, 30, 50);
  }

  @Test
  public void divideIntegerTest() throws JsonProcessingException {
    this.integer(ArithmeticOperation.DIVIDE, (9000 / (43 * 22)), 9000, 43, 22);
  }

  @Test
  public void additionSafeTest() throws JsonProcessingException {
    this.safe(ArithmeticOperation.ADD, BigDecimal.valueOf(3.0).add(BigDecimal.valueOf(1.25)), 3.0, 1.25);
  }

  @Test
  public void subtractionSafeTest() throws JsonProcessingException {
    this.safe(ArithmeticOperation.SUBTRACT, BigDecimal.valueOf(1.25).subtract(BigDecimal.valueOf(1.75)),
        BigDecimal.valueOf(1.25),
        BigDecimal.valueOf(1.75));
  }

  @Test
  public void multiplySafeTest() throws JsonProcessingException {
    this.safe(ArithmeticOperation.MULTIPLY, BigDecimal.valueOf(4.5 * 5.5 * 8.5), 4.5, 5.5, 8.5);
  }

  @Test
  public void divideSafeTest() throws JsonProcessingException {
    this.safe(ArithmeticOperation.DIVIDE, BigDecimal.valueOf(9000 / (90 * 50)), 9000.0, 90, 50);
  }

}