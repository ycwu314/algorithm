package com.example.heap;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by ycwu on 2017/9/25.
 */
public class TestRunningMedian {

    @Test
    public void test() {
        RunningMedian<Integer> runningMedian = new RunningMedian<>();
        // 1
        assertTrue(runningMedian.add(1) - 1 < 1e-10);
        // 1 3
        assertTrue(runningMedian.add(3) - 2 < 1e-10);
        // 1 3 5
        assertTrue(runningMedian.add(5) - 3 < 1e-10);
        // 1 3 5 -10
        assertTrue(runningMedian.add(-10) - 2 < 1e-10);
    }
}
