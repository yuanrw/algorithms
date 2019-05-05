package com.yrw.alogrithms.chapter1.hotorcold;

/**
 * 1.4.34 热还是冷 ~lgN
 * Date: 2019-04-17
 * Time: 14:49
 *
 * @author yrw
 */
public class HotOrColdBetter {

    //TODO: 错误待修正

    private static final int SECRET = 10;

    public int guessNumber(int left, int right, int last) {
        int now = (left + right) / 2;
        while (!isRight(now) && left < right) {
            now = left + right - last;
            if (isHot(last, now)) {
                if (now < last) {
                    right = (Math.min(last, right) + now) / 2 + 1;
                } else {
                    left = (Math.max(last, left) + now) / 2 + 1;
                }
            } else {
                if (now < last) {
                    left = (Math.max(last, left) + now) / 2;
                } else {
                    right = (Math.min(last, right) + now) / 2;
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
        Game game = new Game(10);
        HotOrCold cold = new HotOrCold(game);
        System.out.println(cold.guessNumber(1, 25));
    }
}
