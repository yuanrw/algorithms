package com.yrw.alogrithms.chapter1.hotorcold;

/**
 * Date: 2019-05-05
 * Time: 14:04
 *
 * @author yrw
 */
public class Game {

    private final int secret;
    private int last = Integer.MIN_VALUE;

    public Game(int secret) {
        this.secret = secret;
    }

    public GuessResult guess(int number) {
        if (number == secret) {
            return GuessResult.BINGO;
        }

        int l = last;
        last = number;
        if (l == Integer.MIN_VALUE) {
            return GuessResult.FIRST;
        }
        int lDur = Math.abs(secret - l);
        int nDur = Math.abs(secret - number);
        return lDur < nDur ? GuessResult.COLD : lDur == nDur ? GuessResult.EQUAL : GuessResult.HOT;
    }
}
