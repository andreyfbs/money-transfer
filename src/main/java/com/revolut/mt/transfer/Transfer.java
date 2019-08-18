package com.revolut.mt.transfer;

import com.revolut.mt.account.Account;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transfer {

  private String code;

  private Account fromAccount;

  private Account toAccount;

  private BigDecimal value;

  private LocalDateTime creationDate;

  public Transfer(final Account fromAccount, final Account toAccount, final BigDecimal value) {
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
    this.value = value;
    this.code = UUID.randomUUID().toString();
    this.creationDate = LocalDateTime.now();
  }

  public Account getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(final Account fromAccount) {
    this.fromAccount = fromAccount;
  }

  public Account getToAccount() {
    return toAccount;
  }

  public void setToAccount(final Account toAccount) {
    this.toAccount = toAccount;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(final BigDecimal value) {
    this.value = value;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(final LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public String getCode() {
    return code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Transfer transfer = (Transfer) o;
    return Objects.equals(code, transfer.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }
}
