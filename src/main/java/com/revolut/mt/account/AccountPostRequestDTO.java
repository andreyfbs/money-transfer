package com.revolut.mt.account;

public class AccountPostRequestDTO {

  private String initialAmount;

  public AccountPostRequestDTO() {
  }

  public AccountPostRequestDTO(final String initialAmount) {
    this.initialAmount = initialAmount;
  }

  public String getInitialAmount() {
    return initialAmount;
  }

  public void setInitialAmount(final String initialAmount) {
    this.initialAmount = initialAmount;
  }
}
