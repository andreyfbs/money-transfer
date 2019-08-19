package com.revolut.mt.account;

import static com.revolut.mt.PathConstants.ACCOUNT;
import static com.revolut.mt.PathConstants.ACCOUNTS;

import java.math.BigDecimal;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path(ACCOUNTS)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

  private AccountService accountService;

  public AccountResource() {
    accountService = new AccountService(new AccountData());
  }

  @POST
  public Response postAccount(final AccountPostRequestDTO accountPostRequestDTO) {
    final Account account = accountService.createAccount(new BigDecimal(accountPostRequestDTO.getInitialAmount()));
    return Response.status(Status.CREATED).entity(new AccountPostResponseDTO(account.getAccountNumber())).build();
  }

  @GET
  @Path(ACCOUNT)
  public Response getOneAccount(@PathParam("accountNumber") final String accountNumber) {
    final Account account = accountService.getAccount(accountNumber);
    //Account
    return Response.status(Status.OK).build();
  }

  @GET
  public Response getAllAccount() {
    final List<Account> accounts = accountService.getAllAccounts();
    //Account
    return Response.status(Status.OK).entity(accounts).build();
  }

}