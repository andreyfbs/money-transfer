package com.revolut.mt.account;

public class AccountGetResponseDTO {

  private final String accountNumber;

  public AccountGetResponseDTO(final String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountNumber() {
    return accountNumber;
  }
}
