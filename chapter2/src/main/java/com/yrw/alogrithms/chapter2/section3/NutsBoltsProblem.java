package com.yrw.alogrithms.chapter2.section3;

import com.yrw.algorithms.util.StdRandom;

import java.util.Arrays;

/**
 * 螺帽螺母问题
 * 这是快排的变形，因为nut和bolt都无法和自己比较，所以两个数组相互帮助排序
 * 两个数组必须能完全匹配上
 * Date: 2020/8/14
 * Time: 15:27
 *
 * @author yrw
 */
public class NutsBoltsProblem {

    public void getPairs(int[] nuts, int[] bolts) {
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);
        doSort(nuts, bolts, 0, nuts.length - 1);
    }

    private void doSort(int[] nuts, int[] bolts, int start, int end) {
        if (start >= end) {
            return;
        }

        //先用bolts最后一个元素给nuts排序
        int p = partition(nuts, start, end, bolts[end]);

        //nuts[p]是nuts中最后一个小于等于bolts[end]的元素

        //在用nuts[p]给bolts排序
        partition(bolts, start, end, nuts[p]);

        //如果两个数组能完全匹配（没有落单的），那每一轮排完后，p的位置肯定是一样的
        doSort(nuts, bolts, start, p - 1);
        doSort(nuts, bolts, p + 1, end);
    }

    private int partition(int[] array, int start, int end, int p) {
        int i = start;
        int j = end;

        do {
            //找到第一个大于等于的
            while (i < end && array[i] < p) {
                i++;
            }
            //找到第一个小于等于的
            while (j > 0 && array[j] > p) {
                j--;
            }

            swap(array, i, j);

        } while (i < j);

        //此时array[j]是最后一个小于等于p的数字，也就是p
        return j;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        NutsBoltsProblem nutsBoltsProblem = new NutsBoltsProblem();

        int[] nuts = new int[100];
        int[] bolts = new int[100];
        for (int i = 0; i < nuts.length; i++) {
            nuts[i] = i;
            bolts[i] = i;
        }
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);

        nutsBoltsProblem.getPairs(nuts, bolts);

        System.out.println(Arrays.toString(nuts));
        System.out.println(Arrays.toString(bolts));
    }
}
