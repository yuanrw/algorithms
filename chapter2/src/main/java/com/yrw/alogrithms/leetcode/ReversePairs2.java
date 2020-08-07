package com.yrw.alogrithms.leetcode;

import java.util.Arrays;

/**
 * 数组中的逆序对
 * 经典树状数组问题
 * Date: 2020/8/4
 * Time: 22:32
 *
 * @author yrw
 */
public class ReversePairs2 {

    private int[] a;
    private int[] tree;

    public int reversePairs(int[] nums) {
        a = Arrays.stream(nums).sorted().distinct().toArray();
        tree = new int[nums.length + 1];

        int ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = getIdx(nums[i]);
            ans += query(idx - 1);
            add(idx, 1);
        }
        return ans;
    }

    private int getIdx(int n) {
        return Arrays.binarySearch(a, n);
    }

    private void add(int i, int add) {
        i++;
        while (i < tree.length) {
            tree[i] += add;
            i += i & (-i);
        }
    }

    private int query(int i) {
        i++;
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i);
        }
        return sum;
    }

    public static void main(String[] args) {
        ReversePairs2 reversePairs2 = new ReversePairs2();
        int[][] a = {
            //1
            {-10, -15},
            //0
            {1, 2, 3, 4, 5},
            //0
            {1},
            //5
            {3, 2, 7, 9, -10},
            //0
            {0, 0, 0},
            //0
            {Integer.MAX_VALUE},
            //0
            {},
            //6
            {10, 1, 1, 1, 1, 1, 1}
        };

        for (int[] aa : a) {
            System.out.println(reversePairs2.reversePairs(aa));
        }
    }
}
