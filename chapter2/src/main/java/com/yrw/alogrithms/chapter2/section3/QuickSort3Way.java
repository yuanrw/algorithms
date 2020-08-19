package com.yrw.alogrithms.chapter2.section3;

import com.yrw.alogrithms.chapter2.section1.AbstractSort;

/**
 * 快排：三分法
 * 适用于输入中有很多重复元素的
 * 国旗问题就是这个算法的简单版
 * Date: 2020/8/19
 * Time: 22:38
 *
 * @author yrw
 */
public class QuickSort3Way<T extends Comparable<T>> extends AbstractSort<T> {

    @Override
    public void sort(T[] a) {
        doSort(a, 0, a.length - 1);
    }

    private void doSort(T[] a, int start, int end) {
        if (start >= end) {
            return;
        }

        PartRes res = partition(a, start, end);
        doSort(a, start, res.getI());
        doSort(a, res.getJ(), end);
    }

    private PartRes partition(T[] a, int start, int end) {
        T p = a[start];

        //需要三个指针
        int lte = start + 1;
        int lt = start + 1;
        int gt = end;

        while (true) {

            //lt和lte停留在end / =p的位置
            while (lt <= end && less(a[lte], p)) {
                lt++;
                lte++;
            }
            //gt停留在>=p的位置
            while (less(p, a[gt])) {
                gt--;
            }
            //lte停留在end / =p的最后一个元素后面一个
            while (lte <= end && a[lte].equals(p)) {
                lte++;
            }

            if (lte >= gt) {
                break;
            }

            swap(a, lt, gt);
        }

        swap(a, start, gt);
        //此时gt的位置是p

        //此时满足a[start~lt-1] < p  == a[lt~gt+1] < a[gt+1~end]
        return new PartRes(lt - 1, gt + 1);
    }

    private void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static final class PartRes {

        //<=i的是最后一个比p小的
        private int i;
        //>=j的是第一个比p大的
        private int j;

        public PartRes(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }
    }

    public static void main(String[] args) {
        QuickSort3Way<Integer> quickSort3Way = new QuickSort3Way<>();
        quickSort3Way.runTest(quickSort3Way.getRandomInteger(1000));

        Integer[] lotOfEquals = new Integer[1000];
        lotOfEquals[0] = 2;
        lotOfEquals[1] = 100;
        lotOfEquals[999] = -11;

        for (int i = 2; i < 999; i++) {
            lotOfEquals[i] = 78;
        }
        quickSort3Way.runTest(lotOfEquals);
    }
}
