package com.revolut.mt.transfer;

public class TransferResponseDTO {

  private final String code;
  private final String fromAccountNumber;
  private final String toAccountNumber;
  private final String value;
  private final String creationDate;

  public TransferResponseDTO(final String code, final String fromAccountNumber, final String toAccountNumber, final String value, final String creationDate) {
    this.code = code;
    this.fromAccountNumber = fromAccountNumber;
    this.toAccountNumber = toAccountNumber;
    this.value = value;
    this.creationDate = creationDate;
  }

  public String getCode() {
    return code;
  }

  public String getFromAccountNumber() {
    return fromAccountNumber;
  }

  public String getToAccountNumber() {
    return toAccountNumber;
  }

  public String getValue() {
    return value;
  }

  public String getCreationDate() {
    return creationDate;
  }
}
