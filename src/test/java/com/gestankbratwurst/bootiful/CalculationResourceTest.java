package com.gestankbratwurst.bootiful;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestankbratwurst.bootiful.calculation.ArithmeticOperation;
import com.gestankbratwurst.bootiful.calculation.NumberType;
import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import java.util.Arrays;
import javax.inject.Inject;

@QuarkusTest
public class CalculationResourceTest {

  @Inject
  ObjectMapper objectMapper;

  protected static MockPayload payload(final ArithmeticOperation operation, final NumberType type, final Number... values) {
    final BigDecimal[] preciseValues = Arrays.stream(values)
        .mapToDouble(Number::doubleValue)
        .mapToObj(BigDecimal::new)
        .toArray(BigDecimal[]::new);
    return new MockPayload(preciseValues, operation, type);
  }

  protected static MockPayload payload(final String operation, final String type, final Number... values) {
    final BigDecimal[] preciseValues = values == null ? null : Arrays.stream(values)
        .mapToDouble(Number::doubleValue)
        .mapToObj(BigDecimal::new)
        .toArray(BigDecimal[]::new);
    return new MockPayload(preciseValues, operation, type);
  }

}