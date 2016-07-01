package com.simple.lru.cache;

public interface LRUException<K> {
    /**
     * @param key the key of the value that throws an exception
     * @param throwable the exception that was thrown
     * @return true iff this &lt;key, throwable&gt; pair should not be cached.
     */
    <T extends Throwable> boolean removeEntry(K key, T throwable);
}
