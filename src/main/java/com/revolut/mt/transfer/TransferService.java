package com.revolut.mt.transfer;

import com.revolut.mt.account.Account;
import com.revolut.mt.account.AccountService;
import java.math.BigDecimal;

public class TransferService {

  private final AccountService accountService;
  private final TransferData transferData;

  public TransferService(AccountService accountService, final TransferData transferData) {
    this.accountService = accountService;
    this.transferData = transferData;
  }

  public Transfer transferMoney(Account accountFrom, Account accountTo, BigDecimal transferValue) {

    // Make the Transfer
    accountService.transferBetweenAccounts(accountFrom, accountTo, transferValue);

    // Create a new Transfer
    final Transfer transfer = new Transfer(accountFrom, accountTo, transferValue);
    transferData.save(transfer);

    return transfer;
  }
}
