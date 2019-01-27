package ru.otus.frontend;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import ru.otus.frontend.ChatWebSocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


//будет создавать веб - сокет (???)
public class ChatWebSocketCreator implements WebSocketCreator{

    private Set<ChatWebSocket> users;

    public ChatWebSocketCreator() {
        this.users = Collections.newSetFromMap(new ConcurrentHashMap<ChatWebSocket, Boolean>());
        System.out.println("WebSocketCreator created");
    }


    @Override
    public Object createWebSocket(ServletUpgradeRequest servletUpgradeRequest, ServletUpgradeResponse servletUpgradeResponse) {
        ChatWebSocket socket = new ChatWebSocket(users);
        System.out.println("Socket created");
        return socket;
    }
}
