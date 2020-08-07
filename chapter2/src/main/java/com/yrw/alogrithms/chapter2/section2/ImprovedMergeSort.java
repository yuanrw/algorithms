package com.yrw.alogrithms.chapter2.section2;

import com.yrw.alogrithms.chapter2.section1.SelectSort;

/**
 * 2.2.11
 * Date: 2019-07-23
 * Time: 20:23
 *
 * @author yrw
 */
public class ImprovedMergeSort<T extends Comparable<T>> extends MergeSort<T> {

    private SelectSort<T> selectSort = new SelectSort<>();

    /**
     * 改进归并排序，小数组用选择排序
     *
     * @param a  待排序数组
     * @param lo 起始位置
     * @param hi 结束位置
     */
    @Override
    protected void sort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        if (hi - lo + 1 <= 15) {
            T[] tempA = (T[]) new Comparable[hi - lo + 1];
            System.arraycopy(a, lo, tempA, 0, hi - lo + 1);
            selectSort.sort(tempA);
            System.arraycopy(tempA, 0, a, lo, hi - lo + 1);
            return;
        }
        //辅助数组
        T[] aux = (T[]) new Comparable[a.length];
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void main(String[] args) {
        ImprovedMergeSort<Integer> mergeSort = new ImprovedMergeSort<>();
        mergeSort.runTest(mergeSort.getRandomInteger(1000));
    }
}
