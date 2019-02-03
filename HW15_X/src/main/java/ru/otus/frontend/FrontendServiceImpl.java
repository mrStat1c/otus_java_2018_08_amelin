package ru.otus.frontend;

import ru.otus.client.FrontendService;
import ru.otus.client.MessageSystemContext;
import ru.otus.client.messages.MsgGetUserById;
import ru.otus.db.storage.dataSets.UserDataSet;
import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.MessageSystem;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class FrontendServiceImpl implements FrontendService {
    private final Address address;

    private final MessageSystemContext context;
    private final AtomicLong nextID = new AtomicLong(0);

    public FrontendServiceImpl(String address, MessageSystemContext context) {
        this.address = new Address(address);
        this.context = context;
    }

    private final Map<Long, ResponseHandlers> callbacks = new ConcurrentHashMap<>();

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
        context.setFrontAddress(this.address);
    }

    @Override
    public void completeRequest(long id, String response) {
        callbacks.get(id).responseHandler.accept(response);
    }

    @Override
    public void completeRequest(long id, UserDataSet dataSet) {
        callbacks.get(id).responseHandler.accept(dataSet);
    }

    @Override
    public void handleException(long id, String exceptionInfo) {
        callbacks.get(id).exceptionHandler.accept(exceptionInfo);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

    @Override
    public void getUserById(long userId, Consumer<UserDataSet> onComplete, Consumer<String> onError) {
        long requestId = nextID.getAndIncrement();
        callbacks.put(requestId, new ResponseHandlers(onComplete, onError));

        MsgGetUserById msgRequest = new MsgGetUserById(address, context.getDbAddress(), requestId, userId);
        getMS().sendMessage(msgRequest);
    }


    private class ResponseHandlers {
        public final Consumer responseHandler;
        public final Consumer exceptionHandler;

        public ResponseHandlers(Consumer responseHandler, Consumer exceptionHandler) {
            this.responseHandler = responseHandler;
            this.exceptionHandler = exceptionHandler;
        }
    }
}
