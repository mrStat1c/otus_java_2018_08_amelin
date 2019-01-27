package ru.otus.db.beans;

import ru.otus.db.cache.CacheEngineImpl;
import ru.otus.db.cache.CacheHelper;
import ru.otus.db.storage.dataSets.UserDataSet;

public class CacheEngineBean extends CacheEngineImpl<Long, UserDataSet> {
    public CacheEngineBean(int maxElements, long lifeTimeMs,
                           long idleTimeMs, boolean isEternal) {
        super(maxElements, lifeTimeMs, idleTimeMs,
                isEternal, CacheHelper.SoftEntryFactory());
    }
}

