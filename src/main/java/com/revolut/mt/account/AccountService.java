package com.revolut.mt.account;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class AccountService {

  private final AccountData accountData;

  public AccountService(final AccountData accountData) {
    this.accountData = accountData;
  }

  public Account createAccount(final BigDecimal initialAmount) {
    final Account account = new Account();
    account.setAccountNumber(UUID.randomUUID().toString());
    account.setBalance(initialAmount);
    accountData.save(account);
    return account;
  }

  public Account getAccount(final String accountNumber) {
    return accountData.find(accountNumber);
  }

  List<Account> getAllAccounts() {
    return accountData.findAll();
  }

  public void transferBetweenAccounts(final Account accountFrom, final Account accountTo, final BigDecimal value) {
    accountFrom.fullLock();
    accountTo.fullLock();
    try {
      withdraw(accountFrom, value);
      deposit(accountTo, value);
    } finally {
      accountFrom.fullUnlock();
      accountTo.fullUnlock();
    }
  }

  BigDecimal withdraw(final Account account, final BigDecimal value) {

    if (value.compareTo(account.getBalance()) > 0) {
      throw new InsufficientFundsException();
    }

    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new OperationNegativeAmountException();
    }

    // Subtract From
    BigDecimal balanceResult = account.getBalance().subtract(value);
    account.setBalance(balanceResult);
    return balanceResult;
  }

  BigDecimal deposit(final Account account, final BigDecimal value) {

    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new OperationNegativeAmountException();
    }

    // Add To
    BigDecimal balanceResult = account.getBalance().add(value);
    account.setBalance(balanceResult);
    return balanceResult;
  }

  void deleteAllAccounts() {
    accountData.deleteAll();
  }

}
