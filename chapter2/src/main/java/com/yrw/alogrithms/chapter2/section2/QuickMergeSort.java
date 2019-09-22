package com.yrw.alogrithms.chapter2.section2;

/**
 * 2.2.10
 * Date: 2019-07-23
 * Time: 13:47
 *
 * @author yrw
 */
public class QuickMergeSort<T extends Comparable> extends MergeSort<T> {

    /**
     * 快速合并，把a的后半部分逆序copy到aux，可以省去检测某半边是否用尽的代码
     *
     * @param a
     * @param aux
     * @param lo
     * @param mid
     * @param hi
     */
    @Override
    protected void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        int i = lo, j = hi;
        //已经有序，直接返回
        if (less(a[mid], a[mid + 1])) {
            return;
        }

        //前半部分copy到辅助数组
        System.arraycopy(a, lo, aux, lo, mid - lo + 1);
        //后半部分逆序copy到辅助数组
        for (int ii = mid + 1; ii <= hi; ii++) {
            aux[ii] = a[hi - ii + mid + 1];
        }

        //merge回原数组
        for (int k = lo; k <= hi; k++) {
            if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j--];
            }
        }
    }

    public static void main(String[] args) {
        QuickMergeSort<Integer> mergeSort = new QuickMergeSort<>();
        mergeSort.runTest(mergeSort.getRandomInteger(1000));
    }
}
