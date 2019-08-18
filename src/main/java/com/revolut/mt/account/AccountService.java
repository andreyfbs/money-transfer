package com.revolut.mt.account;

import com.revolut.mt.transfer.AccountData;
import java.math.BigDecimal;
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
      throw new AmountGreaterThanBalanceException();
    }

    // Subtract From
    BigDecimal balanceResult = account.getBalance().subtract(value);
    account.setBalance(balanceResult);
    return balanceResult;
  }


  BigDecimal deposit(final Account account, final BigDecimal value) {
    // Add To
    BigDecimal balanceResult = account.getBalance().add(value);
    account.setBalance(balanceResult);
    return balanceResult;
  }
}
