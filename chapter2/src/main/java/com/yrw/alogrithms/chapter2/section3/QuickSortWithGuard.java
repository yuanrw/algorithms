package com.yrw.alogrithms.chapter2.section3;

import com.yrw.algorithms.util.StdRandom;
import com.yrw.alogrithms.chapter2.section1.AbstractSort;

/**
 * 2.3.17
 * 把传统quick sort改编，增加哨兵
 * 去掉while条件里的边界检查
 * Date: 2020/8/17
 * Time: 23:41
 *
 * @author yrw
 */
public class QuickSortWithGuard<T extends Comparable<T>> extends AbstractSort<T> {

    @Override
    public void sort(T[] a) {
        StdRandom.shuffle(a);

        //把max放入最后作为哨兵
        int m = getMaxIdx(a);
        swap(a, m, a.length - 1);

        doSort(a, 0, a.length - 1);
    }

    private void doSort(T[] a, int start, int end) {
        if (start >= end) {
            return;
        }

        int j = partition(a, start, end);

        doSort(a, start, j - 1);
        doSort(a, j + 1, end);
    }

    private int partition(T[] a, int start, int end) {

        T p = a[start];
        int i = start + 1;
        int j = end;

        while (true) {
            //找到第一个>=p的，i最多可能移动到end+1，即上一轮的切分元素
            //此时i>j，所以不会移动
            while (less(a[i], p)) {
                i++;
            }

            //找到第一个<=p的
            while (less(p, a[j])) {
                j--;
            }

            if (i >= j) {
                break;
            }

            swap(a, i, j);
        }

        //partition完毕
        swap(a, start, j);
        return j;
    }

    private void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private int getMaxIdx(T[] a) {
        int idx = -1;
        T max = null;
        for (int i = 0; i < a.length; i++) {
            if (max == null || less(max, a[i])) {
                max = a[i];
                idx = i;
            }
        }

        return idx;
    }

    public static void main(String[] args) {
        QuickSortWithGuard<Integer> quickSort = new QuickSortWithGuard<>();
        quickSort.runTest(quickSort.getRandomInteger(1000));
    }
}
