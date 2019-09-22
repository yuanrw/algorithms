package com.yrw.alogrithms.chapter2.section1;

import com.yrw.algorithms.util.StdRandom;

import java.util.Arrays;

/**
 * Date: 2019-04-30
 * Time: 13:34
 *
 * @author yrw
 */
public abstract class AbstractSort<T extends Comparable> {

    public abstract void sort(T[] a);

    protected void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    protected void show(T[] a) {
        System.out.println(Arrays.toString(a));
    }

    protected boolean isSorted(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    protected Integer[] getRandomInteger(int size) {
        Integer[] a = new Integer[size];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);

        return a;
    }

    protected void runTest(T[] a) {
        long start = System.currentTimeMillis();
        sort(a);
        long time = System.currentTimeMillis() - start;
        if (!isSorted(a)) {
            throw new RuntimeException("not sorted");
        }
        show(a);
        System.out.println("time: " + time);
    }
}
