package com.revolut.mt;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransferComponentApiTest {

  private static Server server;

  @BeforeClass
  public static void setUp() throws Exception {
    // Create Server
    server = new Server(8060);

    // Configure ServletContextHandler
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);

    // Create Servlet Container
    ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
    jerseyServlet.setInitOrder(0);

    // Tells the Jersey Servlet which REST service/class to load.
    jerseyServlet.setInitParameter(
        "jersey.config.server.provider.packages",
        "com.revolut.mt.account;com.revolut.mt.transfer"
    );

    // Start the server
    server.start();
  }

  @AfterClass
  public static void tearDown() throws Exception {
    server.stop();
  }

  @Test
  public void get_accounts() {

    given().
        when().
        get("http://localhost:8060/accounts").
        then().
        assertThat().statusCode(200);
  }

}
