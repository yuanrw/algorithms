package com.yrw.alogrithms.chapter2.section1;

/**
 * 选择排序
 * Date: 2019-07-13
 * Time: 17:48
 *
 * @author yrw
 */
public class SelectSort<T extends Comparable<T>> extends AbstractSort<T> {

    @Override
    public void sort(T[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = minIndex(a, i);
            if (minIndex != i) {
                exch(a, minIndex, i);
            }
        }
    }

    private int minIndex(T[] a, int start) {
        if (start == a.length - 1) {
            return 0;
        }
        T min = a[start];
        int minIndex = -1;
        for (int i = start + 1; i < a.length; i++) {
            if (less(a[i], min)) {
                min = a[i];
                minIndex = i;
            }
        }
        return minIndex == -1 ? start : minIndex;
    }

    public static void main(String[] args) {
        SelectSort<Integer> selectSort = new SelectSort<>();
        selectSort.runTest(selectSort.getRandomInteger(1000));
    }
}
