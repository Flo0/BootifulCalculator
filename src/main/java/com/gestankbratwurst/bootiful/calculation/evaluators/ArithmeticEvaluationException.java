package com.gestankbratwurst.bootiful.calculation.evaluators;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of BootifulCalculator and was created at the 06.03.2021
 *
 * BootifulCalculator can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class ArithmeticEvaluationException extends Exception {

  public ArithmeticEvaluationException() {
    super("Error while evaluating an arithmetic operation.");
  }

}
