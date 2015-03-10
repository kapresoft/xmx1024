package com.lagnada.xmx1024.config.search;


import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class FibonacciTest {

    private Fibonacci fibonacci;

    @Before
    public void setUp() {
        fibonacci = new Fibonacci();
    }

    @Test
    public void fibonacciReturnsZero() {
        int n = 0;
        assertThat(fibonacci.fib(n)).isEqualTo(0);
    }

    @Test
    public void fibonacciReturnsOne() {
        int n = 1;
        assertThat(fibonacci.fib(n)).isEqualTo(1);
    }

    @Test
    public void fibonacciReturnsValues() {
        int[] n = {2, 3, 4, 5, 6, 7};
        assertThat(fibonacci.fib(n[0])).isEqualTo(1);
        assertThat(fibonacci.fib(n[1])).isEqualTo(2);
        assertThat(fibonacci.fib(n[2])).isEqualTo(3);
        assertThat(fibonacci.fib(n[3])).isEqualTo(5);
        assertThat(fibonacci.fib(n[4])).isEqualTo(8);
        assertThat(fibonacci.fib(n[5])).isEqualTo(13);
    }
}
