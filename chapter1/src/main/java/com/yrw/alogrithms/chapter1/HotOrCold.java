package com.yrw.alogrithms.chapter1;

/**
 * 1.4.34 热还是冷 ~2lgN
 * Date: 2019-04-16
 * Time: 11:05
 *
 * @author yrw
 */
public class HotOrCold {

    //TODO: 错误待修正

    private static final int SECRET = 10;

    public int guessNumber(int left, int right) {
        int last = 1;
        int now = -1;
        while (!isRight(now) && left < right) {
            //第二次
            now = (left + right) / 2;
            if (isHot(last, now)) {
                if (now < last) {
                    right = (last + now) / 2 + 1;
                } else {
                    left = (last + now) / 2 + 1;
                }
            } else {
                if (now < last) {
                    left = (last + now) / 2;
                } else {
                    right = (last + now) / 2;
                }
            }
            last = now;
        }
        if (left >= right) {
            return -1;
        }
        return now;
    }

    private boolean isRight(int n) {
        return n == SECRET;
    }

    private boolean isHot(int last, int now) {
        return (Math.abs(now - SECRET) <= Math.abs(last - SECRET));
    }

    public static void main(String[] args) {
        HotOrCold cold = new HotOrCold();
        System.out.println(cold.guessNumber(1, 25));
    }
}
