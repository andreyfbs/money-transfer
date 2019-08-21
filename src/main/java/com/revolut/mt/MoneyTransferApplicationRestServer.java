package com.revolut.mt;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MoneyTransferApplicationRestServer {

  public static void main(String[] args) throws Exception {
    // Create Server
    Server server = new Server(ApplicationProperties.PORT_SERVER);

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
    server.join();
  }
}
