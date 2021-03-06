package com.gestankbratwurst.bootiful.calculation.evaluators;

import com.gestankbratwurst.bootiful.endpoint.SuccessCalculationResult;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
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
public class I32Evaluator extends ArithmeticEvaluator<Integer> {

  private boolean isIntegerValue(final BigDecimal preciseValue) {
    return preciseValue.doubleValue() % 1 == 0;
  }

  @Override
  public List<BigDecimal> getInvalids(final BigDecimal[] preciseValues) {
    return Arrays.stream(preciseValues).filter(dec -> !this.isIntegerValue(dec)).collect(Collectors.toList());
  }

  @Override
  protected Integer[] fromPreciseValues(final BigDecimal[] preciseValues) {
    return Arrays.stream(preciseValues).filter(Objects::nonNull).map(BigDecimal::intValue).toArray(Integer[]::new);
  }

  @Override
  protected final SuccessCalculationResult<Integer> add(final Integer... values) {
    final Integer result = Arrays.stream(values)
        .filter(Objects::nonNull)
        .mapToInt(Integer::intValue)
        .sum();
    return new SuccessCalculationResult<>(result);
  }

  @Override
  protected SuccessCalculationResult<Integer> subtract(final Integer... values) {
    final Integer base = values[0];
    final Integer result = base - Arrays.stream(Arrays.copyOfRange(values, 1, values.length)).mapToInt(Integer::intValue).sum();
    return new SuccessCalculationResult<>(result);
  }

  @Override
  protected SuccessCalculationResult<Integer> multiply(final Integer... values) {
    final Integer base = values[0];
    final OptionalInt multiplicand = Arrays.stream(Arrays.copyOfRange(values, 1, values.length))
        .mapToInt(Integer::intValue)
        .reduce(this::intBinaryMultiplication);
    final Integer result = base * multiplicand.orElse(1);
    return new SuccessCalculationResult<>(result);
  }

  @Override
  protected SuccessCalculationResult<Integer> divide(final Integer... values) {
    final Integer base = values[0];
    final OptionalInt divisor = Arrays.stream(Arrays.copyOfRange(values, 1, values.length))
        .mapToInt(Integer::intValue)
        .reduce(this::intBinaryMultiplication);
    final Integer result = base / divisor.orElse(1);
    return new SuccessCalculationResult<>(result);
  }

  private int intBinaryMultiplication(final int i, final int j) {
    return i * j;
  }

}