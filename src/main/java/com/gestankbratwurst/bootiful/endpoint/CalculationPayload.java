package com.gestankbratwurst.bootiful.endpoint;

import com.gestankbratwurst.bootiful.calculation.ArithmeticOperation;
import com.gestankbratwurst.bootiful.calculation.NumberType;
import java.math.BigDecimal;
import java.util.Arrays;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 06.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class CalculationPayload {

  private final BigDecimal[] values;
  private final ArithmeticOperation operation;
  private final NumberType type;

  public CalculationPayload(final BigDecimal[] values, final String operation, final String type) {
    this.values = values;
    this.operation = ArithmeticOperation.fromIdentifier(operation);
    this.type = NumberType.fromIdentifier(type);
  }

  public BigDecimal[] getValues() {
    return this.values;
  }

  public ArithmeticOperation getOperation() {
    return this.operation;
  }

  public NumberType getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return "Values: " + Arrays.toString(this.values) + "  Operation: " + this.operation + "  Type: " + this.type;
  }

}