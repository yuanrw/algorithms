package com.yrw.alogrithms.chapter2.section1;

/**
 * Date: 2019-07-13
 * Time: 22:18
 *
 * @author yrw
 */
public class ShellsSort<T extends Comparable> extends AbstractSort<T> {

    @Override
    public void sort(T[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = (int) (Math.pow(3, h) + 1);
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        ShellsSort<Integer> shellsSort = new ShellsSort<>();
        shellsSort.runTest(shellsSort.getRandomInteger(1000));
    }
}
