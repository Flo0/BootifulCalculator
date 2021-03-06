package com.gestankbratwurst.bootiful.calculation.evaluators;

import com.gestankbratwurst.bootiful.calculation.ArithmeticOperation;
import com.gestankbratwurst.bootiful.endpoint.SuccessCalculationResult;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 05.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public abstract class ArithmeticEvaluator<T extends Number> {

  private final EnumMap<ArithmeticOperation, Function<T[], SuccessCalculationResult<T>>> operationMap;

  public ArithmeticEvaluator() {
    this.operationMap = new EnumMap<>(ArithmeticOperation.class);
    this.operationMap.put(ArithmeticOperation.ADD, this::add);
    this.operationMap.put(ArithmeticOperation.SUBTRACT, this::subtract);
    this.operationMap.put(ArithmeticOperation.MULTIPLY, this::multiply);
    this.operationMap.put(ArithmeticOperation.DIVIDE, this::divide);
  }

  public SuccessCalculationResult<T> evaluateFromPreciseValues(final ArithmeticOperation operation, final BigDecimal[] preciseValues) {
    return this.evaluate(operation, this.fromPreciseValues(preciseValues));
  }

  @SafeVarargs
  public final SuccessCalculationResult<T> evaluate(final ArithmeticOperation operation, final T... values) {
    return this.operationMap.get(operation).apply(values);
  }

  public abstract List<BigDecimal> getInvalids(BigDecimal[] preciseValues);

  protected abstract T[] fromPreciseValues(BigDecimal[] preciseValues);

  protected abstract SuccessCalculationResult<T> add(T... values);

  protected abstract SuccessCalculationResult<T> subtract(T... values);

  protected abstract SuccessCalculationResult<T> multiply(T... values);

  protected abstract SuccessCalculationResult<T> divide(T... values);

}
