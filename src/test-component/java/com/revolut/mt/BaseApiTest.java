package com.revolut.mt;

import io.restassured.RestAssured;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.revolut.mt.PathTestConstants.*;

public abstract class BaseApiTest {

    private static Server server;

    @BeforeClass
    public static void setUp() throws Exception {
        // Create Server
        server = new Server(PORT_SERVER);

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

        RestAssured.baseURI = HOST_SERVER;
        RestAssured.port = PORT_SERVER;
    }

    @AfterClass
    public static void tearDown() throws Exception {
        // Stop the server
        server.stop();
    }

}
