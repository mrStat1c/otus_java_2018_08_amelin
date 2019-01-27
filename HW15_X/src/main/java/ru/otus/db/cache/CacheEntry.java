package ru.otus.db.cache;

/**
 * Created by danik_ik.
 */
@SuppressWarnings("WeakerAccess")
public interface CacheEntry<K, V> {

    K getKey();

    V getValue();

    long getCreationTime();

    long getLastAccessTime();

    void setAccessed();
}
