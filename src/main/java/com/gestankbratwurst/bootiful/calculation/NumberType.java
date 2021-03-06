package com.gestankbratwurst.bootiful.calculation;

import com.gestankbratwurst.bootiful.calculation.evaluators.ArithmeticEvaluator;
import com.gestankbratwurst.bootiful.calculation.evaluators.F64Evaluator;
import com.gestankbratwurst.bootiful.calculation.evaluators.I32Evaluator;
import com.gestankbratwurst.bootiful.calculation.evaluators.PreciseFloatEvaluator;
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
public enum NumberType {

  INTEGER("integer", new I32Evaluator()),
  DECIMAL("decimal", new F64Evaluator()),
  SAFE("safe", new PreciseFloatEvaluator());

  public static NumberType fromIdentifier(final String identifier) {
    for (final NumberType numberType : NumberType.values()) {
      if (numberType.identifier.equals(identifier)) {
        return numberType;
      }
    }
    return null;
  }

  public static String getSupportedTypesString() {
    return "[" + Arrays.stream(NumberType.values()).map(NumberType::getIdentifier).collect(Collectors.joining(", ")) + "]";
  }

  private final String identifier;
  private final ArithmeticEvaluator<?> evaluator;

  NumberType(final String identifier, final ArithmeticEvaluator<?> evaluator) {
    this.identifier = identifier;
    this.evaluator = evaluator;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public ArithmeticEvaluator<?> getEvaluator() {
    return this.evaluator;
  }

}