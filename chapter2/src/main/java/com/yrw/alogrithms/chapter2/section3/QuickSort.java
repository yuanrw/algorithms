package com.yrw.alogrithms.chapter2.section3;

import com.yrw.algorithms.util.StdRandom;
import com.yrw.alogrithms.chapter2.section1.AbstractSort;

/**
 * 快速排序
 * Date: 2020/8/7
 * Time: 21:41
 *
 * @author yrw
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {

    @Override
    public void sort(T[] a) {
        //先打乱
        StdRandom.shuffle(a);
        doSort(a, 0, a.length - 1);
    }

    private void doSort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        //切分
        int mid = partition(a, lo, hi);

        //切分完毕，对mid左右数组继续切分
        doSort(a, lo, mid - 1);
        doSort(a, mid + 1, hi);
    }

    /**
     * 快速排序的切分
     *
     * @param a  数组
     * @param lo 开始idx
     * @param hi 结束idx
     * @return 被选中的数字v
     */
    private int partition(T[] a, int lo, int hi) {
        int i = lo + 1;
        int j = hi;

        T v = a[lo];
        while (true) {
            //当心v是数组中最大或者最小的元素

            //找到第一个比v大的数(v不用判断）
            while (i <= hi && less(a[i], v)) {
                i++;
            }
            //找到第一个比v小的数
            //这里可以去掉条件j>lo 因为当j == lo时，a[lo]不可能比自己小
            while (less(v, a[j])) {
                j--;
            }

            //说明第一个比v小的数在第一个比v大的数的左边，数组已经有序
            if (i >= j) {
                break;
            }

            //否则交换
            swap(a, i, j);
        }

        //退出循环的条件只有i>=j，此时a[j]是比v小的数
        //把v放到合适的位置，v和a[j]交换
        swap(a, lo, j);
        return j;
    }

    private void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        QuickSort<Integer> quickSort = new QuickSort<>();
        quickSort.runTest(quickSort.getRandomInteger(1000));
    }
}
