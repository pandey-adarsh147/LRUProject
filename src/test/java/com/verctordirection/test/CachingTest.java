package com.verctordirection.test;

import com.vectordirection.LRUCache;
import org.junit.Test;

/**
 * Created by adarshpandey on 1/13/15.
 */
public class CachingTest {

    @Test
    public void testLRU() {
        System.out.println("=========================================");
        System.out.println("*********** Caching Test Start **********");
        System.out.println("=========================================");

        LRUCache<Integer, Store> storeLRUCache = new LRUCache<>(3);

        storeLRUCache.add(new Store(1, 22));
        storeLRUCache.add(new Store(2, 23));
        storeLRUCache.add(new Store(3, 27));
        storeLRUCache.add(new Store(4, 29));
        storeLRUCache.add(new Store(5, 28));
        storeLRUCache.add(new Store(6, 21));
        storeLRUCache.add(new Store(7, 24));

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert storeLRUCache.size() == 3;

        assert storeLRUCache.getValue(1) == null;

        assert storeLRUCache.getValue(7).getValue() == 24;

        storeLRUCache.shutdown();
    }
}
