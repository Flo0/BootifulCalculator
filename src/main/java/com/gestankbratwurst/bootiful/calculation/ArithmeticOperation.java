package com.gestankbratwurst.bootiful.calculation;

import java.util.Arrays;
import java.util.stream.Collectors;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 05.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public enum ArithmeticOperation {

  ADD("add"),
  SUBTRACT("sub"),
  MULTIPLY("mul"),
  DIVIDE("div");

  public static ArithmeticOperation fromIdentifier(final String identifier) {
    for (final ArithmeticOperation operation : ArithmeticOperation.values()) {
      if (operation.identifier.equals(identifier)) {
        return operation;
      }
    }
    return null;
  }

  private final String identifier;

  ArithmeticOperation(final String identifier) {
    this.identifier = identifier;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public static String getSupportedOperationsString() {
    return "[" +
        Arrays.stream(ArithmeticOperation.values()).map(ArithmeticOperation::getIdentifier).collect(Collectors.joining(", "))
        + "]";
  }

}
