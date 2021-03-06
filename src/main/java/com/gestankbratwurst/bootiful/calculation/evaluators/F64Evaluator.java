package com.gestankbratwurst.bootiful.calculation.evaluators;

import com.gestankbratwurst.bootiful.endpoint.SuccessCalculationResult;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 06.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class F64Evaluator extends ArithmeticEvaluator<Double> {

  @Override
  public List<BigDecimal> getInvalids(final BigDecimal[] preciseValues) {
    return Collections.emptyList();
  }

  @Override
  protected Double[] fromPreciseValues(final BigDecimal[] preciseValues) {
    return Arrays.stream(preciseValues).filter(Objects::nonNull).map(BigDecimal::doubleValue).toArray(Double[]::new);
  }

  @Override
  protected final SuccessCalculationResult<Double> add(final Double... values) {
    final Double result = Arrays.stream(values)
        .filter(Objects::nonNull)
        .mapToDouble(Double::doubleValue)
        .sum();
    return new SuccessCalculationResult<>(result);
  }

  @Override
  protected SuccessCalculationResult<Double> subtract(final Double... values) {
    final Double base = values[0];
    final Double result = base - Arrays.stream(Arrays.copyOfRange(values, 1, values.length)).mapToDouble(Double::doubleValue).sum();
    return new SuccessCalculationResult<>(result);
  }

  @Override
  protected SuccessCalculationResult<Double> multiply(final Double... values) {
    final Double base = values[0];
    final OptionalDouble multiplicand = Arrays.stream(Arrays.copyOfRange(values, 1, values.length))
        .mapToDouble(Double::doubleValue)
        .reduce(this::doubleBinaryMultiplication);
    final Double result = base * multiplicand.orElse(1);
    return new SuccessCalculationResult<>(result);
  }

  @Override
  protected SuccessCalculationResult<Double> divide(final Double... values) {
    final Double base = values[0];
    final OptionalDouble divisor = Arrays.stream(Arrays.copyOfRange(values, 1, values.length))
        .mapToDouble(Double::doubleValue)
        .reduce(this::doubleBinaryMultiplication);
    final Double result = base / divisor.orElse(1);
    return new SuccessCalculationResult<>(result);
  }

  private double doubleBinaryMultiplication(final double i, final double j) {
    return i * j;
  }

}
