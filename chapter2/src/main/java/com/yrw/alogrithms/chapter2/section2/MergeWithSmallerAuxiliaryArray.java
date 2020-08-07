package com.yrw.alogrithms.chapter2.section2;

/**
 * 用更小的辅助数组排序
 * 辅助数组只有原始数组一半大小
 * Date: 2020/8/7
 * Time: 10:54
 *
 * @author yrw
 */
public class MergeWithSmallerAuxiliaryArray<T extends Comparable<T>> extends MergeSort<T> {

    @Override
    public void sort(T[] a) {
        doSort(a, 0, a.length - 1);
    }

    public void doSort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        //辅助数组缩小一半
        T[] help = (T[]) new Comparable[a.length];

        int mid = (lo + hi) / 2;
        doSort(a, lo, mid);
        doSort(a, mid + 1, hi);
        doMerge(a, help, lo, mid, hi);
    }

    public void doMerge(T[] a, T[] help, int lo, int mid, int hi) {
        if (less(a[mid], a[mid + 1])) {
            return;
        }

        //复制左边一半
        System.arraycopy(a, lo, help, lo, mid - lo + 1);

        int i = lo;
        int j = mid + 1;
        int k = lo;

        while (i < mid + 1 && j < hi + 1) {
            if (less(help[i], a[j])) {
                a[k++] = help[i++];
            } else {
                a[k++] = a[j++];
            }
        }

        while (i < mid + 1) {
            a[k++] = help[i++];
        }

        while (j < hi + 1) {
            a[k++] = a[j++];
        }
    }

    public static void main(String[] args) {
        MergeWithSmallerAuxiliaryArray<Integer> merge = new MergeWithSmallerAuxiliaryArray<>();
        merge.runTest(merge.getRandomInteger(1000));
    }
}
