package com.yrw.alogrithms.chapter2.section1;

/**
 * 2.1.25
 * Date: 2019-07-20
 * Time: 14:05
 *
 * @author yrw
 */
public class InsertionX<T extends Comparable> extends InsertSort<T> {

    @Override
    public void sort(T[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            T temp = a[i];

            while (j > 0 && less(temp, a[j - 1])) {
                j--;
            }
            //j即a[i]要插入的位置，把a[j]到a[i-1]的数字向后挪动
            //用这个系统函数比手动copy更快
            if (i != j) {
                System.arraycopy(a, j, a, j + 1, i - j);
            }

            a[j] = temp;
        }
    }

    public static void main(String[] args) {
        InsertionX<Integer> insertionX = new InsertionX<>();
        insertionX.runTest(insertionX.getRandomInteger(1000));
    }
}
