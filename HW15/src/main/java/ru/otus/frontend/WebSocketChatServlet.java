package ru.otus.frontend;

import org.eclipse.jetty.websocket.servlet.*;
import ru.otus.frontend.ChatWebSocketCreator;

import java.util.concurrent.TimeUnit;

//этот сервлет будет слушать какой-то URL, чтобы к нему можно было обращаться
public class WebSocketChatServlet extends WebSocketServlet {
    private static final long LOGOUT_TIME = TimeUnit.MINUTES.toMillis(10);

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        webSocketServletFactory.setCreator(new ChatWebSocketCreator());
    }
}


//1. включаем веб - сервер
//2. веб-сервер подклюем веб-сокет-сервлет (??)
//3. клиентская сторона обращается к определенному URL (к веб-сокет сервлету ??)
//4. веб-сокет сервлет по запросу создает веб-сокет на нашей стороне (??)(1 клиент можнт установить много веб-сокетов)
//веб-сокет на стороне клиента + веб-сокет на стороне сервера = чтото типа соединения


//далее вроде тоже самое другими словами
//5. клиент создает у себя веб-сокет (??), веб сокет зная адрес сервера, обращается на сервер
//6. на сервере создается веб-сокет, веб-сокеты клиента и сервера соединяются и получается дублексное соединение

//аннотация WebSocket
//аннотация OnWebSocketConnect - метод, который вызывается, когда произошел коннект
//аннотация OnWebSocketMessage -
//аннотация OnWebSocketError -
//аннотация OnWebSocketClose -
//это все аннотации на стороне сервера, которе сработают, если клиентская сторона их инициирует


//    В ДЗ нужно разбить приложение на 4 части:
//        - клиент (на JS в браузере),
//        - FrontendService на сервере в котором будет по одному объекту вебсокета на клиента,
//        - DBService который будет обращаться к базе и
//        - MessageSystem, которая передает сообщения от FrontendService в DBService и обратно.