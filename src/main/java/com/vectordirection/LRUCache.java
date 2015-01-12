package com.vectordirection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by adarshpandey on 1/12/15.
 */
public class LRUCache<Key, Item extends CacheableItem<Key>> implements Runnable {

    private final int maxSize;
    private final Integer mutex = 1;
    private Semaphore semaphore = new Semaphore(0);

    private final Map<Key, Item> cache;

    private boolean isKeepRunning = true;

    public LRUCache(int cacheSize) {
        cache = new HashMap<>();
        maxSize = cacheSize;

        isKeepRunning = true;

        Thread thread = new Thread(this);
        thread.start();
    }

    public int size() {
        return cache.size();
    }

    public Item getValue(Key key) {
        return cache.get(key);
    }

    public void add(Item item) {
        synchronized (mutex) {
            if (cache.containsKey(item.getKey())) {
                cache.remove(item.getKey());
            }
            cache.put(item.getKey(), item);
            semaphore.release();
        }
    }

    public void shutdown() {
        synchronized (mutex) {
            isKeepRunning = false;
            semaphore.release();
        }
    }

    public void prune() {
        synchronized (mutex) {
            Iterator<Map.Entry<Key, Item>> itemIterator = cache.entrySet().iterator();
            while (itemIterator.hasNext()) {
                itemIterator.next();
                if (cache.size() > maxSize) {
                    itemIterator.remove();
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public void run() {
        while (isKeepRunning) {
            try {
                semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS);
                semaphore.drainPermits();
                if (isKeepRunning) {
                    prune();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
