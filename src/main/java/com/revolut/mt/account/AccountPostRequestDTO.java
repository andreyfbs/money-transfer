package com.revolut.mt.account;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class AccountPostRequestDTO {

  @NotNull
  @DecimalMin(value = "0.00")
  @Digits(integer = 8, fraction = 2)
  private String initialAmount;

  public AccountPostRequestDTO() {
  }

  public String getInitialAmount() {
    return initialAmount;
  }

  public void setInitialAmount(final String initialAmount) {
    this.initialAmount = initialAmount;
  }
}
