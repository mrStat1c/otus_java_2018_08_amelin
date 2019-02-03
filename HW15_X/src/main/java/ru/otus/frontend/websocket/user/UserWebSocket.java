package ru.otus.frontend.websocket.user;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.client.FrontendService;
import ru.otus.db.storage.dataSets.UserDataSet;
import ru.otus.db.storage.serialization.GsonHelper;

import java.io.IOException;

@WebSocket
public class UserWebSocket {
    private Session session;

    @Autowired
    private FrontendService frontendService;

    @OnWebSocketMessage
    public void onMessage(String data) {
        sendToClient(sendToDb(data));
    }

    private String sendToDb(String data) {
        readUser(data);
        return "ACCEPTED";
    }

    private void readUser(String jsonStr) {
        Long userID = Long.parseLong(jsonStr);
        try {
            frontendService.getUserById(userID, this::sendUserDatasetToClient, this::handleErrorMessage);
        } catch (Exception e) {
            thereWasError(e);
            return;
        }
    }


    private void updateUser(String jsonStr) {
        UserDataSet userDataSet = GsonHelper.userFromJson(jsonStr);
        try {
            frontendService.update(userDataSet, this::sendUserDatasetToClient, this::handleErrorMessage);
        } catch (Exception e) {
            thereWasError(e);
            return;
        }
    }

    private void thereWasError(Exception e) {
        sendToClient("ERROR");
    }

    private void handleErrorMessage(String errorMessage) {
        sendToClient("ERROR:" + new Gson().toJson(errorMessage));
    }

    private void addUser(String jsonStr) {
        UserDataSet userDataSet = GsonHelper.userFromJson(jsonStr);
        try {
            frontendService.addUser(userDataSet, this::sendUserDatasetToClient, this::handleErrorMessage);
        } catch (Exception e) {
            thereWasError(e);
            return;
        }
    }

    private void sendUserDatasetToClient(UserDataSet userDataSet) {
        String response;
        try {
            response = "USER:" + GsonHelper.toJson(userDataSet);
        } catch (Exception e) {
            thereWasError(e);
            return;
        }
        sendToClient(response);
    }

    public void sendToClient(String data) {
        try {
            this.getSession().getRemote().sendString(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        setSession(session);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        // do nothing
    }

    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
}
