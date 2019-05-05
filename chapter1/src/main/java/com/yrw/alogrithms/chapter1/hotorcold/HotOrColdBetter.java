package com.yrw.alogrithms.chapter1.hotorcold;

/**
 * 1.4.34 热还是冷 ~lgN
 * Date: 2019-04-17
 * Time: 14:49
 *
 * @author yrw
 */
public class HotOrColdBetter {

    private Game game;

    public HotOrColdBetter(Game game) {
        this.game = game;
    }

    public int guessNumber(int left, int right) {
        GuessResult result = game.guess(left);
        if (result.equals(GuessResult.EQUAL)) {
            return left;
        }

        while (left <= right) {
            int last = game.getLast();
            int now = left + right - last;
            result = game.guess(now);
            if (result.equals(GuessResult.BINGO)) {
                return now;
            }

            int mid = (left + right) / 2;
            if (now > last) {
                switch (result) {
                    case HOT:
                        left = mid + 1;
                        break;
                    case COLD:
                        right = mid - 1;
                        break;
                    default:
                }
            } else {
                switch (result) {
                    case HOT:
                        right = mid - 1;
                        break;
                    case COLD:
                        left = mid + 1;
                        break;
                    default:
                }
            }

            if (game.guess(mid) == GuessResult.BINGO) {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Game game = new Game(97);
        HotOrColdBetter cold = new HotOrColdBetter(game);

        System.out.println(cold.guessNumber(1, 98));
    }
}
