package com.revolut.mt.transfer;

import com.revolut.mt.account.Account;
import com.revolut.mt.account.AccountService;
import java.math.BigDecimal;
import java.util.Set;

public class TransferService {

  private final AccountService accountService;
  private final TransferData transferData;

  public TransferService(final AccountService accountService, final TransferData transferData) {
    this.accountService = accountService;
    this.transferData = transferData;
  }

  public Transfer transferMoney(final Account accountFrom, final Account accountTo, final BigDecimal transferValue) {

    // Make the Transfer
    accountService.transferBetweenAccounts(accountFrom, accountTo, transferValue);

    // Create a new Transfer
    final Transfer transfer = new Transfer(accountFrom, accountTo, transferValue);
    transferData.save(transfer);

    return transfer;
  }

  Set<Transfer> getAllTransfers() {
    return transferData.findAll();
  }

  void deleteAllTransfers() {
    transferData.deleteAll();
  }
}
