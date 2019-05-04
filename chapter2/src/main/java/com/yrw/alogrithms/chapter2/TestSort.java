package com.yrw.alogrithms.chapter2;

import java.util.Arrays;

/**
 * Date: 2019-04-30
 * Time: 13:34
 *
 * @author yrw
 */
public abstract class TestSort {

    public abstract void sort(int[] a);

    protected boolean less(int a, int b) {
        return a < b;
    }

    protected void show(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    protected boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    protected void runTest(int[] a) {
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
