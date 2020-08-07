package com.yrw.alogrithms.chapter2.section2;

import com.yrw.alogrithms.chapter2.section1.AbstractSort;

/**
 * 归并排序
 * Date: 2019-07-21
 * Time: 20:46
 *
 * @author yrw
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {

    @Override
    public void sort(T[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 归并排序
     *
     * @param a  待排序数组
     * @param lo 起始位置
     * @param hi 结束位置
     */
    protected void sort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        //辅助数组
        T[] aux = (T[]) new Comparable[a.length];
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    protected void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        //已经有序，直接返回
        if (less(a[mid], a[mid + 1])) {
            return;
        }

        //全部copy到辅助数组
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        //merge回原数组
        for (int k = lo; k <= hi; k++) {
            if (j > hi) {
                a[k] = aux[i++];
            } else if (i > mid) {
                a[k] = aux[j++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort<Integer> mergeSort = new MergeSort<>();
        mergeSort.runTest(mergeSort.getRandomInteger(1000));
    }
}
