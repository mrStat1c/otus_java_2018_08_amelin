package ru.otus.user;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public class UserWebSocketCreator  implements WebSocketCreator {
    @Override
    public Object createWebSocket(ServletUpgradeRequest servletUpgradeRequest, ServletUpgradeResponse servletUpgradeResponse) {
        UserWebSocket webSocket = new UserWebSocket();
        webSocket.init();
        return webSocket;
    }
}
