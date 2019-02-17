package ru.otus;


import ru.otus.hibernateStorage.DbServiceHibernateImpl;

public class Main {

    public static void main(String[] args) throws Exception {
        LoadingEmulator loadingEmulator = new LoadingEmulator(new DbServiceHibernateImpl());

        ServerWrapper serverWrapper = new ServerWrapper();
        serverWrapper.start();
        serverWrapper.join();
    }
}
