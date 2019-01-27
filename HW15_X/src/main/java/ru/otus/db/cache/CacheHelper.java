package ru.otus.db.cache;

import java.util.function.BiFunction;

public class CacheHelper {
    public static <K, V> BiFunction<K, V, CacheEntry<K, V>> SoftEntryFactory() {
        return CacheEntrySoft::new;
    }

    public static <K, V> BiFunction<K, V, CacheEntry<K, V>> StrongEntryFactory() {
        return CacheEntrySoft::new;
    }

    public static <K, V> CacheEngine<K, V> getSoftCache(int size) {
        return new CacheEngineImpl<>(size, 0, 0, true,
                ru.otus.db.cache.CacheHelper.SoftEntryFactory());
    }
}

