package com.revolut.mt.account;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountNotFoundApiException extends RuntimeException implements ExceptionMapper<AccountNotFoundApiException> {

  public AccountNotFoundApiException() {
    super("Account Not Found");
  }

  @Override
  public Response toResponse(AccountNotFoundApiException exception) {
    return Response.status(NOT_FOUND).entity(exception.getMessage())
        .type(MediaType.APPLICATION_JSON).build();
  }
}
