package com.yrw.alogrithms.leetcode;

import java.util.Arrays;

/**
 * 经典国旗问题，颜色分类
 * Date: 2020/8/4
 * Time: 22:26
 *
 * @author yrw
 */
public class SortColor {

    public void sortColors(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int cur = 0;
        while (cur <= r) {
            if (nums[cur] == 0) {
                //前面的数字都是处理过的，不可能是2
                swap(nums, l++, cur++);
            } else if (nums[cur] == 2) {
                //无法确定从后面换过来是什么数字，所以还要处理，cur不移动
                swap(nums, cur, r--);
            } else {
                cur++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        SortColor solution = new SortColor();

        int[] a = {1, 2, 0};
        solution.sortColors(a);

        System.out.println(Arrays.toString(a));
    }
}
