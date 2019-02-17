package ru.otus.user;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import java.util.concurrent.TimeUnit;

public class UserServlet extends WebSocketServlet {
    private final static long LOGOUT_TIME = TimeUnit.MINUTES.toMillis(10);

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator(new UserWebSocketCreator());
    }
}
