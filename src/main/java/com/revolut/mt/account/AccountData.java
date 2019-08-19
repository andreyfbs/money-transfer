package com.revolut.mt.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountData {

  private static final Map<String, Account> accounts = new HashMap<>();

  public Account find(String accountNumber) {
    return accounts.get(accountNumber);
  }

  public List<Account> findAll() {
    return new ArrayList<>(accounts.values());
  }

  public void save(Account account) {
    accounts.put(account.getAccountNumber(), account);
  }

}
