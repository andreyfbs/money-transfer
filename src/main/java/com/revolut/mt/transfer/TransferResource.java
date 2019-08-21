package com.revolut.mt.transfer;

import static com.revolut.mt.PathConstants.TRANSFERS;

import com.revolut.mt.account.Account;
import com.revolut.mt.account.AccountData;
import com.revolut.mt.account.AccountService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
  public Response transferMoney(@NotNull @Valid final TransferPostRequestDTO transferPostRequestDTO) {
    final Account accountFrom = accountService.getAccount(transferPostRequestDTO.getAccountNumberFrom());
    final Account accountTo = accountService.getAccount(transferPostRequestDTO.getAccountNumberTo());
    final Transfer transfer = transferService.transferMoney(accountFrom, accountTo, new BigDecimal(transferPostRequestDTO.getTransferValue()));
    return Response.status(Status.CREATED).entity(transfer.getCode()).build();
  }

  @GET
  public Response getAllTransfers() {
    final Set<Transfer> transfers = transferService.getAllTransfers();
    final List<TransferResponseDTO> transferDTO = transfers.stream().map(this::getTransferDTO).collect(Collectors.toList());
    return Response.status(Status.OK).entity(transferDTO).build();
  }

  @DELETE
  public Response deleteAllTransfers() {
    transferService.deleteAllTransfers();
    return Response.status(Status.OK).build();
  }

  private TransferResponseDTO getTransferDTO(Transfer transfer) {
    return new TransferResponseDTO(transfer.getCode(), transfer.getFromAccount().getAccountNumber(), transfer.getToAccount().getAccountNumber(),
        transfer.getValue().toString(), transfer.getCreationDate().toString());
  }
}
