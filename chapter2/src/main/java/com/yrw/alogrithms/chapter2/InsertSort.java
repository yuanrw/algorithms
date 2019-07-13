package com.yrw.alogrithms.chapter2;

import com.yrw.algorithms.util.StdRandom;

/**
 * 插入排序
 * Date: 2019-07-13
 * Time: 21:47
 *
 * @author yrw
 */
public class InsertSort<T extends Comparable> extends AbstractSort<T> {
    @Override
    public void sort(T[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        InsertSort<Integer> insertSort = new InsertSort<>();

        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        StdRandom.shuffle(a);
        insertSort.runTest(a);
    }
}
