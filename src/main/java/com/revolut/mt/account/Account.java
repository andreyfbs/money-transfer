package com.revolut.mt.account;

import java.math.BigDecimal;
import java.util.Objects;

public class Account extends ConcurrentObject {

  private String accountNumber;

  private BigDecimal balance;

  public String getAccountNumber() {
    return accountNumber;
  }

  void setAccountNumber(final String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BigDecimal getBalance() {
    partialLock();
    try {
      return balance;
    } finally {
      partialUnlock();
    }
  }

  void setBalance(final BigDecimal balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Account account = (Account) o;
    return Objects.equals(accountNumber, account.accountNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountNumber);
  }

  @Override
  public String toString() {
    return "Account{" +
        "accountNumber='" + accountNumber + '\'' +
        ", balance=" + balance +
        '}';
  }
}
