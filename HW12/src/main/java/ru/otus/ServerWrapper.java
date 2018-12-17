package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.DbService.DbService;
import ru.otus.DbService.DbServiceImpl;
import ru.otus.DbService.DefaultConfigurationHolder;

import java.io.IOException;

public class ServerWrapper {
    private String indexPageDirectory;
    private int port;
    private Server server;

    ServerWrapper() throws IOException {
        indexPageDirectory = "../HW12/public_html";
        port = 8090;
        configureServer();
    }

    ServerWrapper(String indexPageDirectory, int port) throws IOException {
        this.indexPageDirectory = indexPageDirectory;
        this.port = port;
        configureServer();
    }

    private void configureServer() throws IOException {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(indexPageDirectory);
        DbService dbService = new DbServiceImpl(DefaultConfigurationHolder.getConfiguration());

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        UserServlet userServlet = new UserServlet(dbService);
        context.addServlet(new ServletHolder(userServlet), "/admin");

        server = new Server(port);
        server.setHandler(new HandlerList(resourceHandler, context));
    }

    public void start() throws Exception {
        server.start();
    }

    public void join() throws Exception {
        server.join();
    }
}
