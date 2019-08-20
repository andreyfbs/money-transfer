package com.revolut.mt.account;

public class AccountResponseDTO {

  private final String accountNumber;

  private final String balanceNumber;

  public AccountResponseDTO(String accountNumber, String balanceNumber) {
    this.accountNumber = accountNumber;
    this.balanceNumber = balanceNumber;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getBalanceNumber() {
    return balanceNumber;
  }
}
