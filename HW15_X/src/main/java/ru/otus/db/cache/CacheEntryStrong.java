package ru.otus.db.cache;

/**
 * Created by danik_ik.
 */
@SuppressWarnings("WeakerAccess")
public class CacheEntryStrong<K, V> extends CacheEntryBase<K, V> {
    private final V value;

    public CacheEntryStrong(K key, V value) {
        super(key);
        this.value = value;
    }

    @Override
    public V getValue() {
        return value;
    }
}
