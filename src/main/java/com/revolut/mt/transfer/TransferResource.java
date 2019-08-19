package com.revolut.mt.transfer;

import com.revolut.mt.account.Account;
import com.revolut.mt.account.AccountData;
import com.revolut.mt.account.AccountService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.math.BigDecimal;

import static com.revolut.mt.PathConstants.TRANSFERS;

@Path(TRANSFERS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransferResource {

  private TransferService transferService;
  private AccountService accountService;

  public TransferResource() {
    accountService = new AccountService(new AccountData());
    transferService = new TransferService(accountService, new TransferData());
  }

  @POST
  public Response transferMoney(final TransferPostRequestDTO transferPostRequestDTO) {
    final Account accountFrom = accountService.getAccount(transferPostRequestDTO.getAccountNumberFrom());
    final Account accountTo = accountService.getAccount(transferPostRequestDTO.getAccountNumberTo());
    final Transfer transfer = transferService.transferMoney(accountFrom, accountTo, new BigDecimal(transferPostRequestDTO.getTransferValue()));
    return Response.status(Status.CREATED).entity(transfer.getCode()).build();
  }
}
