package com.yrw.alogrithms.chapter2.section1;

/**
 * 2.1.24
 * Date: 2019-07-20
 * Time: 13:09
 *
 * @author yrw
 */
public class InsertSortWithSentry<T extends Comparable> extends InsertSort<T> {

    @Override
    public void sort(T[] a) {
        T min = a[0];
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], min)) {
                min = a[i];
                index = i;
            }
        }
        if (index != 0) {
            exch(a, 0, index);
        }

        for (int i = 1; i < a.length; i++) {
            for (int j = i; less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        InsertSortWithSentry<Integer> insertSort = new InsertSortWithSentry<>();
        insertSort.runTest(insertSort.getRandomInteger(1000));
    }
}
