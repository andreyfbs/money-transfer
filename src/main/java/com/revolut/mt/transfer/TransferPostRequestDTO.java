package com.revolut.mt.transfer;

public class TransferPostRequestDTO {

  private String accountNumberFrom;
  private String accountNumberTo;
  private String transferValue;

  public TransferPostRequestDTO() {
  }

  public String getAccountNumberFrom() {
    return accountNumberFrom;
  }

  public String getAccountNumberTo() {
    return accountNumberTo;
  }

  public String getTransferValue() {
    return transferValue;
  }

  public void setAccountNumberFrom(String accountNumberFrom) {
    this.accountNumberFrom = accountNumberFrom;
  }

  public void setAccountNumberTo(String accountNumberTo) {
    this.accountNumberTo = accountNumberTo;
  }

  public void setTransferValue(String transferValue) {
    this.transferValue = transferValue;
  }
}
