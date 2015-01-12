package com.verctordirection.test;

import com.vectordirection.CacheableItem;

/**
 * Created by adarshpandey on 1/13/15.
 */
public class Store implements CacheableItem<Integer> {

    private Integer key;
    private Integer value;

    public Store(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }
}
