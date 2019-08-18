package com.revolut.mt.transfer;

import java.util.HashSet;
import java.util.Set;

public class TransferData {

  private static final Set<Transfer> transfers = new HashSet<>();

  public void save(Transfer transfer) {
    transfers.add(transfer);
  }
}
