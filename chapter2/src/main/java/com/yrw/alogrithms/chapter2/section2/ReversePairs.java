package com.yrw.alogrithms.chapter2.section2;

/**
 * 归并的方法，另一种树状数组的方法见 ReversePairs2
 * 2.2.19
 * Date: 2019-09-22
 * Time: 16:50
 *
 * @author yrw
 */
public class ReversePairs<T extends Comparable<T>> extends MergeSort<T> {

    public int countReversePairs(T[] a) {
        if (a == null || a.length <= 1) {
            return 0;
        }
        return doCount(a, 0, a.length - 1);
    }

    private int doCount(T[] a, int start, int end) {
        if (start >= end) {
            return 0;
        }

        T[] helpArray = (T[]) new Comparable[a.length];
        int mid = (start + end) / 2;
        int cnt = 0;

        cnt += doCount(a, start, mid);
        cnt += doCount(a, mid + 1, end);
        cnt += mergeAndCount(a, helpArray, start, mid, end);

        return cnt;
    }

    /**
     * 计算左数组大于右数组数字的个数
     *
     * @param a   数组
     * @param aux 辅助数组
     * @param lo  数组开始
     * @param mid 右数组开始
     * @param hi  数组结束
     * @return
     */
    private int mergeAndCount(T[] a, T[] aux, int lo, int mid, int hi) {
        //已经有序，没有逆序对
        if (less(a[mid], a[mid + 1])) {
            return 0;
        }
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int cnt = 0;
        int i = lo;
        int j = mid + 1;

        int k = lo;

        while (i < mid + 1 || j < hi + 1) {
            if (i > mid) {
                a[k++] = aux[j++];
            } else if (j > hi) {
                a[k++] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) > 0) {
                //逆序，说明左数组从当前数到结尾所有的数都大于aux[j]，因为是排序的
                cnt += mid - i + 1;
                a[k++] = aux[j++];
            } else {
                a[k++] = aux[i++];
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        ReversePairs<Integer> reversePairs = new ReversePairs<>();
        Integer[][] a = {
            //1
            {-10, -15},
            //0
            {1, 2, 3, 4, 5},
            //0
            {1},
            //5
            {3, 2, 7, 9, -10},
            //0
            {0, 0, 0},
            //0
            {Integer.MAX_VALUE},
            //0
            {},
            //6
            {10, 1, 1, 1, 1, 1, 1}
        };

        for (Integer[] aa : a) {
            System.out.println(reversePairs.countReversePairs(aa));
        }
    }
}
