package ru.otus;

public class Main {

    public static void main(String[] args) throws Exception {

        ServerWrapper server = new ServerWrapper();
        server.start();
        server.join();
    }
}
