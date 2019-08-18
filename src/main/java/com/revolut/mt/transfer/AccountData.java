package com.revolut.mt.transfer;

import com.revolut.mt.account.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountData {

  private static final Map<String, Account> accounts = new HashMap<>();

  public Account find(String accountNumber) {
    return accounts.get(accountNumber);
  }

  public void save(Account account) {
    accounts.put(account.getAccountNumber(), account);
  }

}
