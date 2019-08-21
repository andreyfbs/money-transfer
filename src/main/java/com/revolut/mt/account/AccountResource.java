package com.revolut.mt.account;

import static com.revolut.mt.PathConstants.ACCOUNTS;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
  public Response postAccount(@Valid @NotNull final AccountPostRequestDTO accountPostRequestDTO) {
    final Account account = accountService.createAccount(new BigDecimal(accountPostRequestDTO.getInitialAmount()));
    final AccountResponseDTO accountResponseDTO = getAccountDTO(account);
    return Response.status(Status.CREATED).entity(accountResponseDTO).build();
  }

  @GET
  @Path("{accountNumber}")
  public Response getAccount(@PathParam("accountNumber") final String accountNumber) {
    final Account account = accountService.getAccount(accountNumber);
    final AccountResponseDTO accountResponseDTO = getAccountDTO(account);
    return Response.status(Status.OK).entity(accountResponseDTO).build();
  }

  @GET
  public Response getAllAccount() {
    final List<Account> accounts = accountService.getAllAccounts();
    final List<AccountResponseDTO> accountsDTO = accounts.stream().map(this::getAccountDTO).collect(Collectors.toList());
    return Response.status(Status.OK).entity(accountsDTO).build();
  }

  @DELETE
  public Response deleteAllAccounts() {
    accountService.deleteAllAccounts();
    return Response.status(Status.OK).build();
  }

  private AccountResponseDTO getAccountDTO(Account account) {
    return new AccountResponseDTO(account.getAccountNumber(), account.getBalance().toString());
  }

}
