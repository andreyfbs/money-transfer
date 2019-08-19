package com.revolut.mt.account;

public class AccountPostResponseDTO {

  private final String accountNumber;

  public AccountPostResponseDTO(final String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountNumber() {
    return accountNumber;
  }
}
