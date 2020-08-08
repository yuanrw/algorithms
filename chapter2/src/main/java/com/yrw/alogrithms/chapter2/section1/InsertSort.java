package com.yrw.alogrithms.chapter2.section1;

/**
 * 插入排序
 * Date: 2019-07-13
 * Time: 21:47
 *
 * @author yrw
 */
public class InsertSort<T extends Comparable<T>> extends AbstractSort<T> {
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
        insertSort.runTest(insertSort.getRandomInteger(1000));
    }
}
