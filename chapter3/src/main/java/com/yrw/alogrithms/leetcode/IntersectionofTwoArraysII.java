package com.yrw.alogrithms.leetcode;

import java.util.*;

/**
 * Date: 2020/8/4
 * Time: 22:20
 *
 * @author yrw
 */
public class IntersectionofTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> inter = new ArrayList<>();

        int a = 0;
        int b = 0;
        while (a < nums1.length && b < nums2.length) {
            if (nums1[a] == nums2[b]) {
                inter.add(nums1[a]);
                a++;
                b++;
            } else if (nums1[a] < nums2[b]) {
                a++;
            } else {
                b++;
            }
        }
        return inter.stream().mapToInt(i -> i).toArray();
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        List<Integer> inter = new ArrayList<>();

        //假设数组长度不会超过Integer.MAX
        Map<Integer, Integer> map = new HashMap<>();
        //遍历短的那个数组
        int s = nums1.length <= nums2.length ? 1 : 2;
        int[] shortNum = s == 1 ? nums1 : nums2;
        int[] longNum = s == 1 ? nums2 : nums1;
        for (int i = 0; i < shortNum.length; i++) {
            map.put(shortNum[i], map.getOrDefault(shortNum[i], 0) + 1);
        }
        for (int i = 0; i < longNum.length; i++) {
            int count = map.getOrDefault(longNum[i], 0);
            if (count > 0) {
                inter.add(longNum[i]);
                map.put(longNum[i], count - 1);
            }
        }
        return inter.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        IntersectionofTwoArraysII intersection = new IntersectionofTwoArraysII();

        int[] a1 = {1, 2, 2, 1};
        int[] b1 = {2, 2};
        print(intersection.intersect2(a1, b1));

        int[] a2 = {};
        int[] b2 = {2, 2};
        print(intersection.intersect2(a2, b2));

        int[] a3 = {1, 2, 2, 1};
        int[] b3 = {};
        print(intersection.intersect2(a3, b3));

        int[] a4 = {1, 2, 2, 1};
        int[] b4 = {1, 2, 2, 1};
        print(intersection.intersect2(a4, b4));

        int[] a5 = {1, 2, 2, 1};
        int[] b5 = {5, 4, -1, -99};
        print(intersection.intersect2(a5, b5));

        int[] a6 = {Integer.MAX_VALUE, -99999, 2, 1};
        int[] b6 = {-99999, 2};
        print(intersection.intersect2(a6, b6));

        int[] a7 = {Integer.MIN_VALUE};
        int[] b7 = {Integer.MIN_VALUE};
        print(intersection.intersect2(a7, b7));
    }

    private static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
