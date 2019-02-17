package ru.otus;

import ru.otus.storage.DBService;
import ru.otus.storage.dataSets.UserDataSet;

public class LoadingEmulator {
    private static final int USER_COUNT = 4;

    private final DBService dbService;

    public LoadingEmulator(DBService dbService) {
        this.dbService = dbService;
        createData();
    }


    private void createData() {
        for (int i = 0; i <= USER_COUNT; i++) {
            UserDataSet user = new UserDataSet();
            user.setName("User " + i);
            user.setStreet("Noname street, " + i);
            dbService.save(user);
        }
    }
}
