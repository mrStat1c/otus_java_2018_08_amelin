package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class Main {
    private static final String htmlIndexDirectory = "../HW12/public_html";//папка, в которой лежат статические ресурсы (страница)
    private static final int port = 8090;

    public static void main(String[] args) throws Exception {

        //обработчик для статических ресурсов (указываем, где будут лежать статические ресурсы)
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(htmlIndexDirectory);

        //обработчик сервелетов. Добавляем сервлет UserServlet
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(UserServlet.class, "/admin");

        //Устанавливаем для сервера порт, обработчик ресурсов и обработчик сервлетов
        Server server = new Server(port);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
