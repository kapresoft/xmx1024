package com.lagnada.xmx1024.integration;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

    @Test
    public void synchronizedHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "there");
        map.put(null, "hi");

        Map<String, String> syncMap = Collections.synchronizedMap(map);
        syncMap.put(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void concurrentHashMap() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("hello", "there");
        map.put(null, "hi");
    }

}
