package com.revolut.mt.account;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InsufficientFundsException extends RuntimeException implements ExceptionMapper<InsufficientFundsException> {

  public InsufficientFundsException() {
    super("Insufficient Funds");
  }

  @Override
  public Response toResponse(InsufficientFundsException exception) {
    return Response.status(BAD_REQUEST).entity(exception.getMessage())
        .type(MediaType.APPLICATION_JSON).build();
  }
}
