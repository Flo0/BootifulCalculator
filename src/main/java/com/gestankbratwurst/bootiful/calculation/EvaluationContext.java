package com.gestankbratwurst.bootiful.calculation;

import com.gestankbratwurst.bootiful.calculation.evaluators.ArithmeticEvaluator;
import com.gestankbratwurst.bootiful.endpoint.CalculationPayload;
import com.gestankbratwurst.bootiful.endpoint.CalculationResult;
import com.gestankbratwurst.bootiful.endpoint.ErrorResult;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 06.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class EvaluationContext {

  private final CalculationPayload payload;

  public EvaluationContext(final CalculationPayload payload) {
    this.payload = payload;
  }
  
  public CalculationResult produceResult() {
    final PayloadFault fault = this.checkPayloadState();
    if (fault.isJustified()) {
      return new ErrorResult(fault.getMessage());
    }
    return this.evaluatePayload();
  }

  private CalculationResult evaluatePayload() {
    final ArithmeticEvaluator<?> evaluator = this.payload.getType().getEvaluator();
    final BigDecimal[] preciseValues = this.payload.getValues();
    try {
      return evaluator.evaluateFromPreciseValues(this.payload.getOperation(), preciseValues);
    } catch (final Exception e) {
      return new ErrorResult(e.getMessage());
    }
  }

  private PayloadFault checkPayloadState() {
    if (this.payload.getValues() == null || this.payload.getValues().length < 1) {
      return PayloadFault.FAULTY_VALUE;
    }
    if (this.payload.getOperation() == null) {
      return PayloadFault.FAULTY_OPERATION.withAppendix(ArithmeticOperation.getSupportedOperationsString());
    }
    if (this.payload.getType() == null) {
      return PayloadFault.FAULTY_NUMBER_TYPE.withAppendix(NumberType.getSupportedTypesString());
    }
    final List<BigDecimal> invalidValues = this.payload.getType().getEvaluator().getInvalids(this.payload.getValues());
    if (!invalidValues.isEmpty()) {
      return PayloadFault.FAULTY_VALUE_ELEMENT.withAppendix(Arrays.toString(invalidValues.toArray()));
    }
    return PayloadFault.NONE;
  }

  private enum PayloadFault {

    FAULTY_VALUE(true, "Faulty value property. Please specify at least one value."),
    FAULTY_VALUE_ELEMENT(true, "The following elements do not match the given type: "),
    FAULTY_OPERATION(true, "Unsupported operation property. Please specify one of: "),
    FAULTY_NUMBER_TYPE(true, "Unsupported type property. Please specify one of: "),
    NONE(false, "");

    PayloadFault(final boolean justified, final String message) {
      this.justified = justified;
      this.message = message;
    }

    public boolean isJustified() {
      return this.justified;
    }

    public String getMessage() {
      return this.message + (this.appendix == null ? "" : this.appendix.toString());
    }

    public PayloadFault withAppendix(final Object appendix) {
      this.appendix = appendix;
      return this;
    }

    private Object appendix;
    private final boolean justified;
    private final String message;

  }

}