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
public class ReversePair {

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
        ReversePair reversePair = new ReversePair();
        int[][] a = {
            //[2, 2, 2 ,2 ,2, 4, 3, 2, 0, 0]
            {-9, -9, -9, -9, -9, 2, 1, 0, -10, -10},
            //[0, 1, 1, 2]
            {5, 2, 6, 1},
            //[0, 0, 0]
            {1, 2, 3},
            //[2, 1, 0]
            {3, 2, 1},
            //[0, 0]
            {1, 1},
            //[0]
            {Integer.MAX_VALUE},
            //[]
            {},
            //[6, 0, 0, 0, 0, 0, 0]
            {10, 1, 1, 1, 1, 1, 1}
        };

        for (int[] aa : a) {
            System.out.println(reversePair.reversePairs(aa));
        }
    }
}
