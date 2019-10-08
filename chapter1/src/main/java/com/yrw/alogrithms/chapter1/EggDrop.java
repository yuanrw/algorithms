package com.yrw.alogrithms.chapter1;

/**
 * Date: 2019-09-29
 * Time: 08:29
 *
 * @author yrw
 */
public class EggDrop {

    private int[][] result;

    /**
     * @param i 剩余鸡蛋个数
     * @param j 楼层高度
     * @return
     */
    private int dp(int i, int j) {
        if (result[i][j] != -1) {
            return result[i][j];
        }
        if (i == 1) {
            return j;
        }
        if (j <= 1) {
            return j;
        }
        int lowest = log(j + 1);
        if (i >= lowest) {
            result[i][j] = lowest;
            return lowest;
        }

        int left = 1, right = j;
        while (left <= right) {
            int k = (left + right) / 2;
            int broken = dp(i - 1, k - 1);
            result[i - 1][k - 1] = broken;
            int notBroken = dp(i, j - k);
            result[i][j - k] = notBroken;

            if (broken < notBroken) {
                left = k + 1;
            } else if (broken > notBroken) {
                right = k - 1;
            } else {
                return notBroken + 1;
            }
        }
        //没找到，最小值就在left或者right中
        return Math.min(Math.max(dp(i - 1, left - 1), dp(i, j - left)),
            Math.max(dp(i - 1, right - 1), dp(i, j - right))) + 1;
    }

    private static int log(int x) {
        double r = (Math.log(x) / Math.log(2));
        if ((r == Math.floor(r)) && !Double.isInfinite(r)) {
            return (int) r;
        } else {
            return (int) r + 1;
        }
    }

    public static void main(String[] args) {
        EggDrop eggDrop = new EggDrop();
        int m = 5, n = 100;
        eggDrop.result = new int[m + 1][n + 1];

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                eggDrop.result[i][j] = -1;
            }
        }

        System.out.println(eggDrop.dp(m, n));
    }
}
