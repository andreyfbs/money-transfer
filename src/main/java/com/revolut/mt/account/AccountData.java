package com.revolut.mt.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountData {

  private static final Map<String, Account> accounts = new ConcurrentHashMap<>();

  Account find(String accountNumber) {
    if (accounts.containsKey(accountNumber)) {
      return accounts.get(accountNumber);
    }
    throw new AccountNotFoundApiException();

  }

  List<Account> findAll() {
    return new ArrayList<>(accounts.values());
  }

  void save(Account account) {
    accounts.put(account.getAccountNumber(), account);
  }

  void deleteAll() {
    accounts.clear();
  }

}
