package com.yrw.alogrithms.leetcode;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机打乱数组
 * 经典洗牌算法
 * Date: 2020/8/7
 * Time: 17:37
 *
 * @author yrw
 */
public class ShuffleArray {

    private final int[] nums;
    private final int[] shuffledNums;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        this.shuffledNums = new int[nums.length];
        System.arraycopy(nums, 0, shuffledNums, 0, nums.length);
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        System.arraycopy(nums, 0, shuffledNums, 0, nums.length);
        return shuffledNums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = 0; i < shuffledNums.length; i++) {
            int a = ThreadLocalRandom.current().nextInt(0, i + 1);
            if (a != i) {
                int tmp = shuffledNums[a];
                shuffledNums[a] = shuffledNums[i];
                shuffledNums[i] = tmp;
            }
        }
        return shuffledNums;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        ShuffleArray shuffleArray = new ShuffleArray(a);

        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(shuffleArray.shuffle()));
            shuffleArray.reset();
        }
    }
}
