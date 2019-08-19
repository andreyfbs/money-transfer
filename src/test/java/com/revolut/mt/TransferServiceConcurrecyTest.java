package com.revolut.mt;

import com.revolut.mt.account.Account;
import com.revolut.mt.account.AccountService;
import com.revolut.mt.account.AccountData;
import com.revolut.mt.transfer.TransferData;
import com.revolut.mt.transfer.TransferService;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransferServiceConcurrecyTest {

  private static ExecutorService pool;

  private AccountService accountService;
  private TransferService transferService;

  @BeforeClass
  public static void setupClass() {
    pool = Executors.newFixedThreadPool(10);
  }

  @Before
  public void setup() {
    final AccountData accountData = new AccountData();
    final TransferData transferData = new TransferData();
    accountService = new AccountService(accountData);
    transferService = new TransferService(accountService, transferData);
  }

  @AfterClass
  public static void clear() throws InterruptedException {
    pool.awaitTermination(3, TimeUnit.SECONDS);
    pool.shutdown();
  }

  @Test
  public void transferMoneyAmong3Accounts() throws InterruptedException {

    // Arrange
    final Account account1 = accountService.createAccount(new BigDecimal("1000.00"));
    final Account account2 = accountService.createAccount(new BigDecimal("1000.00"));
    final Account account3 = accountService.createAccount(new BigDecimal("1000.00"));

    final BigDecimal transferValue = new BigDecimal("1.00");
    final BigDecimal accountBalance1Expected = new BigDecimal("800.00");
    final BigDecimal accountBalance2Expected = new BigDecimal("1000.00");
    final BigDecimal accountBalance3Expected = new BigDecimal("1200.00");
    final int numberOfTransfers = 200;

    // Act
    for (int i = 0; i < numberOfTransfers; i++) {
      pool.submit(() -> {
        transferService.transferMoney(account1, account2, transferValue);
      });
    }
    for (int i = 0; i < numberOfTransfers; i++) {
      pool.submit(() -> {
        transferService.transferMoney(account2, account3, transferValue);
      });
    }

    // Wait for 1 seconds
    Thread.sleep(1000);

    // Assert
    Assert.assertEquals(accountBalance1Expected, account1.getBalance());
    Assert.assertEquals(accountBalance2Expected, account2.getBalance());
    Assert.assertEquals(accountBalance3Expected, account3.getBalance());
  }

}
