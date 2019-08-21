package com.revolut.mt.transfer;

import java.util.HashSet;
import java.util.Set;

public class TransferData {

  private static final Set<Transfer> transfers = new HashSet<>();

  void save(Transfer transfer) {
    transfers.add(transfer);
  }

  Set<Transfer> findAll() {
    return transfers;
  }

  void deleteAll() {
    transfers.clear();
  }
}
