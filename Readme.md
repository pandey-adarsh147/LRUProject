Generic LRU cache


How to use:

LRUCache<Integer, Store> storeLRUCache = new LRUCache<>(3);

storeLRUCache.add(new Store(1, 22));
storeLRUCache.add(new Store(2, 23));
storeLRUCache.add(new Store(3, 27));
storeLRUCache.add(new Store(4, 29));
storeLRUCache.add(new Store(5, 28));
storeLRUCache.add(new Store(6, 21));
storeLRUCache.add(new Store(7, 24));

storeLRUCache.shutdown();