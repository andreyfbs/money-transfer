package com.revolut.mt.transfer;

import com.revolut.mt.account.Account;
import com.revolut.mt.account.AccountService;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TransferServiceTest {

  @Mock
  private AccountService accountService;

  @InjectMocks
  private TransferService transferService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    transferService = new TransferService(accountService, new TransferData());
  }

  @Test
  public void transferMoneySuccessful() {

    // Arrange
    Account accountFrom = accountService.createAccount(new BigDecimal("5.00"));
    Account accountTo = accountService.createAccount(new BigDecimal("7.30"));

    final BigDecimal transferValue = new BigDecimal("3.00");

    // Act
    Transfer transfer = transferService.transferMoney(accountFrom, accountTo, transferValue);

    // Assert
    Mockito.verify(accountService, Mockito.times(1)).transferBetweenAccounts(Mockito.any(), Mockito.any(), Mockito.any());
    Assert.assertEquals(transferValue, transfer.getValue());
    Assert.assertNotNull(transfer.getCode());
  }
}
