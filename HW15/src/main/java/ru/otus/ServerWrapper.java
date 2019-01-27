package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.otus.frontend.WebSocketChatServlet;

import java.io.IOException;

public class ServerWrapper {
    private String indexPageDirectory;
    private int port;
    private Server server;

    ServerWrapper() throws IOException {
        indexPageDirectory = "../HW15/public_html";
        port = 8090;
        configureServer();
    }

    ServerWrapper(String indexPageDirectory, int port) throws IOException {
        this.indexPageDirectory = indexPageDirectory;
        this.port = port;
        configureServer();
    }

    private void configureServer() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(indexPageDirectory);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(WebSocketChatServlet.class, "/chat");
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
