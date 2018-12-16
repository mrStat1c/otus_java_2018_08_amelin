package ru.otus;

public class Main {
    public static void main(String[] args) throws Exception {

        ServerWrapper serverWrapper = new ServerWrapper();
        serverWrapper.start();
        serverWrapper.join();
    }
}
