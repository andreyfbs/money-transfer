package com.revolut.mt.transfer;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class TransferPostRequestDTO {

  @NotNull
  private String accountNumberFrom;

  @NotNull
  private String accountNumberTo;

  @NotNull
  @DecimalMin(value = "0.00")
  @Digits(integer = 8, fraction = 2)
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
