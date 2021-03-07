package com.gestankbratwurst.bootiful.calculation.evaluators;

import com.gestankbratwurst.bootiful.endpoint.SuccessCalculationResult;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 06.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class PreciseFloatEvaluator extends ArithmeticEvaluator<BigDecimal> {

  @Override
  public List<BigDecimal> getInvalids(final BigDecimal[] preciseValues) {
    return Collections.emptyList();
  }

  @Override
  protected BigDecimal[] fromPreciseValues(final BigDecimal[] preciseValues) {
    return preciseValues;
  }

  @Override
  protected SuccessCalculationResult<BigDecimal> add(final BigDecimal... values) {
    final BigDecimal result = Arrays.stream(values).filter(Objects::nonNull).reduce(new BigDecimal(0), BigDecimal::add);
    return new SuccessCalculationResult<>(result);
  }

  @Override
  protected SuccessCalculationResult<BigDecimal> subtract(final BigDecimal... values) {
    final BigDecimal result = Arrays.stream(Arrays.copyOfRange(values, 1, values.length))
        .filter(Objects::nonNull)
        .reduce(values[0], BigDecimal::subtract);
    return new SuccessCalculationResult<>(result);
  }

  @Override
  protected SuccessCalculationResult<BigDecimal> multiply(final BigDecimal... values) {
    final BigDecimal result = Arrays.stream(values)
        .filter(Objects::nonNull)
        .reduce(new BigDecimal(1), BigDecimal::multiply);
    return new SuccessCalculationResult<>(result);
  }

  @Override
  protected SuccessCalculationResult<BigDecimal> divide(final BigDecimal... values) {
    final BigDecimal result = Arrays.stream(Arrays.copyOfRange(values, 1, values.length))
        .filter(Objects::nonNull)
        .reduce(values[0], this::preciseBinaryDivision);
    return new SuccessCalculationResult<>(result);
  }

  private BigDecimal preciseBinaryDivision(final BigDecimal base, final BigDecimal divisor) {
    return base.divide(divisor, 100, RoundingMode.HALF_EVEN).stripTrailingZeros();
  }

}