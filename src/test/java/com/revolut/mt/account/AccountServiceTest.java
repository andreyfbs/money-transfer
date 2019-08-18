package com.revolut.mt.account;

import com.revolut.mt.transfer.AccountData;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class AccountServiceTest {

  private AccountService accountService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    accountService = new AccountService(new AccountData());
  }

  @Test
  public void withdrawSuccessful() {
    // Arrange
    final Account account = accountService.createAccount(new BigDecimal("5.34"));
    final BigDecimal amountWithdraw = new BigDecimal("3.45");
    final BigDecimal expectedBalance = new BigDecimal("1.89");

    // Act
    final BigDecimal currentBalance = accountService.withdraw(account, amountWithdraw);

    // Assert
    Assert.assertEquals(expectedBalance, currentBalance);
  }

  @Test
  public void depositSuccessful() {
    // Arrange
    final Account account = accountService.createAccount(new BigDecimal("7.98"));
    final BigDecimal amountDeposit = new BigDecimal("5.23");
    final BigDecimal expectedBalance = new BigDecimal("13.21");

    // Act
    final BigDecimal currentBalance = accountService.deposit(account, amountDeposit);

    // Assert
    Assert.assertEquals(expectedBalance, currentBalance);
  }

  @Test
  public void createAccountSuccessful() {
    // Arrange
    final BigDecimal initialAmount = new BigDecimal("5.23");

    // Act
    final Account accountCreated = accountService.createAccount(initialAmount);

    // Assert
    Assert.assertEquals(accountCreated.getBalance(), initialAmount);
    Assert.assertNotNull(accountCreated.getAccountNumber());
  }

  @Test
  public void transferBetweenAccountsSuccessful() {

  }
}