package com.yrw.alogrithms.chapter2.section2;

/**
 * 2.2.16
 * Date: 2019-07-28
 * Time: 15:12
 *
 * @author yrw
 */
public class NatureSort<T extends Comparable> extends MergeSort<T> {

    @Override
    public void sort(T[] a) {
        int lo = 0, hi, mid = 0;

        T[] aux = (T[]) new Comparable[a.length];

        while (lo != 0 || mid < a.length - 1) {
            lo = mid = hi = 0;
            while (mid < a.length - 1 && hi < a.length - 1) {
                mid = lo;

                //找到第一个有序数组
                while (mid < a.length - 1 && a[mid].compareTo(a[mid + 1]) < 0) {
                    mid++;
                }

                //如果mid在之后还有数据
                if (mid < a.length - 1) {
                    hi = mid + 1;
                    //找到第二个有序数组
                    while (hi < a.length - 1 && a[hi].compareTo(a[hi + 1]) < 0) {
                        hi++;
                    }

                    merge(a, aux, lo, mid, hi);

                    if (hi < a.length - 1) {
                        lo = hi + 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NatureSort<Integer> natureSort = new NatureSort<>();
        natureSort.runTest(natureSort.getRandomInteger(1000));
    }
}
