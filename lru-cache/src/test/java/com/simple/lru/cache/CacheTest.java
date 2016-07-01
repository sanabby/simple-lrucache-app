package com.simple.lru.cache;

import com.simple.lru.lruexes.LRUExecutor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.CoreMatchers.equalTo;

@SuppressWarnings("unused")
@RunWith(value = Parameterized.class)
public class CacheTest {
    private final static Logger logger = LoggerFactory.getLogger(CacheTest.class);
    LRUCache<String, Integer> cache = new LRUCache<String, Integer>();
    private ExecutorService executorService;

    @BeforeClass
    public static void beforeClass() {
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{new LRUExecutor()},
                {Executors.newCachedThreadPool()}};
        return Arrays.asList(data);
    }

    public CacheTest(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Test(timeout = 5000)
    public void testPutGet() throws Throwable {
        final AtomicBoolean fromCache = new AtomicBoolean();
        cache.put("abc", 2);
        cache.put("def", 3);
        cache.put("foo", 4);
        cache.put("xab", 5);
        cache.put("xbc", 6);
        cache.put("rtg", 7);
        cache.put("tyu", 8);
        cache.put("gyu", 9);
        cache.put("hyb", 10);
        cache.put("zsr", 11);
        cache.put("cdw", 12);
        cache.get("zsr");
        
    }
   
    @Test(timeout = 5000)
    public void testDisplay() throws Throwable {
        final AtomicBoolean fromCache = new AtomicBoolean();
        cache.display();  
    }
}