package com.lagnada.xmx1024.config.search;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class RecursiveBinarySearchTest {

    private RecursiveBinarySearch search;

    @Before
    public void setUp() {
        search = new RecursiveBinarySearch(1, 3, 4, 12, 21, 32, 44, 58, 61);
    }

    @Test
    public void binarySearch21Works() {
        assertThat(search.search(21)).isTrue();
    }

    @Test
    public void binarySearch32Works() {
        assertThat(search.search(32)).isTrue();
    }
}
