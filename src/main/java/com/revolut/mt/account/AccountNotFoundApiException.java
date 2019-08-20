package com.revolut.mt.account;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class AccountNotFoundApiException extends RuntimeException
        implements ExceptionMapper<AccountNotFoundApiException> {

    public AccountNotFoundApiException() {
        super("Account Not Found");
    }

    @Override
    public Response toResponse(AccountNotFoundApiException exception) {
        return Response.status(NOT_FOUND).entity(exception.getMessage())
                .type(MediaType.APPLICATION_JSON).build();
    }
}
