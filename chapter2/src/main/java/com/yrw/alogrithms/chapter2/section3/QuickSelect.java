package com.yrw.alogrithms.chapter2.section3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * quick select
 * 利用快排的思想，用分区的方式找出k th的元素
 * Date: 2020/8/9
 * Time: 10:56
 *
 * @author yrw
 */
public class QuickSelect {

    /**
     * 快速选择
     *
     * @param nums 目标数组
     * @param k    从小到大第k个元素
     * @return
     */
    public int select(int[] nums, int k) {
        k = k - 1;
        shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        while (hi > lo) {
            int j = partition(nums, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return nums[k];
            }
        }

        //当lo == hi时，假设输入一定合法，那lo即k
        return nums[k];
    }

    private int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {
            //找到 >= v的
            while (a[++i] < v) {
                if (i == hi) {
                    break;
                }
            }
            //找到 <= v的
            while (a[--j] > v) {
            }

            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j);

        return j;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private int[] shuffle(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int a = ThreadLocalRandom.current().nextInt(0, i + 1);
            if (a != i) {
                int tmp = nums[a];
                nums[a] = nums[i];
                nums[i] = tmp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        QuickSelect select = new QuickSelect();

        //假设length>=k
        int[][] a = {
            {0, 0, 0, 0, 0, 0, 0},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, -1},
            {100, -100},
            {5, 10, 99, -29, 232, 235, 0, -1},
            {3, 2, 3, 1, 2, 4, 5, 5, 6}
        };

        for (int[] aa : a) {
            System.out.println(select.select(aa, 2));
        }
    }
}
