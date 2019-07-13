package com.yrw.alogrithms.chapter2;

import com.yrw.algorithms.util.StdRandom;

/**
 * Date: 2019-07-13
 * Time: 22:18
 *
 * @author yrw
 */
public class ShellsSort<T extends Comparable> extends AbstractSort<T> {

    @Override
    public void sort(T[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = (int) (Math.pow(3, h) + 1);
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        ShellsSort<Integer> insertSort = new ShellsSort<>();

        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        StdRandom.shuffle(a);
        insertSort.runTest(a);
    }
}
