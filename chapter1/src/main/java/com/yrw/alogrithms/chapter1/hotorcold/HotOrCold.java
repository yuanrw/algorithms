package com.yrw.alogrithms.chapter1.hotorcold;

/**
 * 1.4.34 热还是冷 ~2lgN
 * Date: 2019-04-16
 * Time: 11:05
 *
 * @author yrw
 */
public class HotOrCold {

    private Game game;

    public HotOrCold(Game game) {
        this.game = game;
    }

    public int guessNumber(int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            GuessResult result = game.guess(left);
            if (result.equals(GuessResult.BINGO)) {
                return left;
            }

            result = game.guess(right);
            if (result.equals(GuessResult.BINGO)) {
                return right;
            }

            if (left == right) {
                break;
            }

            switch (result) {
                case HOT:
                    left = mid + 1;
                    break;
                case COLD:
                    right = mid - 1;
                    break;
                case EQUAL:
                    left = mid;
                default:
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Game game = new Game(97);
        HotOrCold cold = new HotOrCold(game);

        System.out.println(cold.guessNumber(1, 98));
    }
}
