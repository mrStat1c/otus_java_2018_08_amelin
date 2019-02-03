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
//        int delimiterPosition = data.indexOf(':');
//
//        String command = delimiterPosition >= 0 ?
//                data.substring(0, delimiterPosition) : data;
//        String tail = delimiterPosition >= 0 ?
//                data.substring(delimiterPosition + 1) : "";
//
//        switch (command) {
//            case "ADD": {
//                addUser(tail);
//                break;
//            }
//            case "UPDATE": {
//                updateUser(tail);
//                break;
//            }
//            case "READ": {
//                readUser(tail);
//                break;
//            }
//            default: {return "REJECTED";}
//        }
        readUser(data);
        return "ACCEPTED";
    }

    private void readUser(String jsonStr) {
//        UserID userID = new Gson().fromJson(jsonStr, UserID.class);
        Long userID = Long.parseLong(jsonStr);
        try {
//            frontendService.getUserById(userID.id, this::sendUserDatasetToClient, this::handleErrorMessage);
            frontendService.getUserById(userID, this::sendUserDatasetToClient, this::handleErrorMessage);
        } catch (Exception e) {
            thereWasError(e);
            return;
        }
    }

    private class UserID {
        Long id;
    }

    private void updateUser(String jsonStr) {
        UserDataSet userDataSet = GsonHelper.userFromJson(jsonStr);
        try {
            frontendService.update(userDataSet, this::sendUserDatasetToClient, this::handleErrorMessage);
        } catch (Exception e) {
            // В реальности ошибка при сохранении сюда не попадёт, т.к. текущая реализация не предусматривает
            // возврат информации об ошибке через систему сообщений
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
        String response = null;
        try {
            response = "USER:" + GsonHelper.toJson(userDataSet);
        } catch (Exception e) {
            // usrDataset загружается из БД лениво, поэтому ошибка ловится здесь
            // (в момент обращения к полям)
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
