package com.simple.lru.cache;
public final class LRUExceptions {
    private LRUExceptions() {
       // not called
    }
    public static <K> LRUException<K> alwaysRetain() {
        return new LRUException<K>() {
            @Override
            public <T extends Throwable> boolean removeEntry(K key, T throwable) {
                return false;
            }
        };
    }

    public static <K> LRUException<K> alwaysRemove() {
        return new LRUException<K>() {
            @Override
            public <T extends Throwable> boolean removeEntry(K key, T throwable) {
                return true;
            }
        };
    }

    public static <K> LRUException<K> removeOn(final Class<? extends Throwable> cls) {
        return new LRUException<K>() {
            @Override
            public <T extends Throwable> boolean removeEntry(K key, T throwable) {
                return cls.isAssignableFrom(throwable.getClass());
            }
        };
    }

    public static <K> LRUException<K> not(final LRUException<K> strategy) {
        return new LRUException<K>() {
            @Override
            public <T extends Throwable> boolean removeEntry(K key, T throwable) {
                return !strategy.removeEntry(key, throwable);
            }
        };
    }

    @SuppressWarnings({"unchecked"})
    public static <K> LRUException<K> and(final LRUException<K>... strategies) {
        return new LRUException<K>() {
            @Override
            public <T extends Throwable> boolean removeEntry(K key, T throwable) {
                for (LRUException<K> strategy : strategies) {
                    if (!strategy.removeEntry(key, throwable)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    @SuppressWarnings({"unchecked"})
    public static <K> LRUException<K> or(final LRUException<K>... strategies) {
        return new LRUException<K>() {
            @Override
            public <T extends Throwable> boolean removeEntry(K key, T throwable) {
                for (LRUException<K> strategy : strategies) {
                    if (strategy.removeEntry(key, throwable)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

}
