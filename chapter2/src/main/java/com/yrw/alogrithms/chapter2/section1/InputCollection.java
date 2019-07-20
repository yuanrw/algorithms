package com.yrw.alogrithms.chapter2.section1;

import java.util.Arrays;
import java.util.Random;

/**
 * 罕见输入
 * Date: 2019-07-20
 * Time: 15:37
 *
 * @author yrw
 */
public class InputCollection {

    /**
     * 主键只有两种值
     * 2.1.28
     *
     * @param n 数组长度
     * @return
     */
    public static double[] twoInput(int n) {
        Long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = random.nextDouble() > 0.5 ? 1.0 : 0.0;
        }
        return a;
    }

    /**
     * 有序数组
     *
     * @param n 数组长度
     * @return
     */
    public static double[] sortedInput(int n) {
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        return a;
    }


    /**
     * 逆序数组
     *
     * @param n 数组长度
     * @return
     */
    public static double[] reversedInput(int n) {
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = n - i - 1;
        }
        return a;
    }

    /**
     * 数组所有主键相同
     *
     * @param n 数组长度
     * @return
     */
    public static double[] oneInput(int n) {
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = 0;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(InputCollection.oneInput(100)));
        System.out.println(Arrays.toString(InputCollection.reversedInput(100)));
        System.out.println(Arrays.toString(InputCollection.sortedInput(100)));
        System.out.println(Arrays.toString(InputCollection.twoInput(100)));
    }
}
