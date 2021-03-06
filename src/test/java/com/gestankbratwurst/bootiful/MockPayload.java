package com.gestankbratwurst.bootiful;

import com.gestankbratwurst.bootiful.calculation.ArithmeticOperation;
import com.gestankbratwurst.bootiful.calculation.NumberType;
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
public class MockPayload {

  public MockPayload(final BigDecimal[] values, final String operation, final String type) {
    this.values = values;
    this.operation = operation;
    this.type = type;
  }

  public MockPayload(final BigDecimal[] values, final ArithmeticOperation operation, final NumberType type) {
    this.values = values;
    this.operation = operation.getIdentifier();
    this.type = type.getIdentifier();
  }

  private final BigDecimal[] values;
  private final String operation;
  private final String type;

}
