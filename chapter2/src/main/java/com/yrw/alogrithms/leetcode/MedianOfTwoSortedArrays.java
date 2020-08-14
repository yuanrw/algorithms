package com.yrw.alogrithms.leetcode;

/**
 * Date: 2020/8/13
 * Time: 22:18
 *
 * @author yrw
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double k = (nums1.length + nums2.length + 1) / 2.0;
        int i = 0;
        int j = 0;
        int last = 0;

        int cnt = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                if (++cnt == k) {
                    return nums1[i];
                } else if (k % 1 != 0 && cnt == Math.ceil(k)) {
                    return (nums1[i] + last) / 2.0;
                }
                last = nums1[i];
                i++;
            } else {
                if (++cnt == k) {
                    return nums2[j];
                } else if (k % 1 != 0 && cnt == Math.ceil(k)) {
                    return (nums2[j] + last) / 2.0;
                }
                last = nums2[j];
                j++;
            }
        }

        //b已经遍历完
        while (i < nums1.length) {
            if (++cnt == k) {
                return nums1[i];
            } else if (k % 1 != 0 && cnt == Math.ceil(k)) {
                return (nums1[i] + last) / 2.0;
            }
            last = nums1[i];
            i++;
        }

        //a已经遍历完
        while (j < nums2.length) {
            if (++cnt == k) {
                return nums2[j];
            } else if (k % 1 != 0 && cnt == Math.ceil(k)) {
                return (nums2[j] + last) / 2.0;
            }
            last = nums2[j];
            j++;
        }

        return -1;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays arrays = new MedianOfTwoSortedArrays();
        int[][] a = {
            {1, 2},
            {3}
        };
        System.out.println(arrays.findMedianSortedArrays(a[0], a[1]));

        int[][] b = {
            {1, 2},
            {3, 4}
        };
        System.out.println(arrays.findMedianSortedArrays(b[0], b[1]));

        int[][] c = {
            {1, 3},
            {2, 4}
        };
        System.out.println(arrays.findMedianSortedArrays(c[0], c[1]));

        int[][] d = {
            {1, 89, 99, 232},
            {3}
        };
        System.out.println(arrays.findMedianSortedArrays(d[0], d[1]));

        int[][] e = {
            {-999},
            {Integer.MIN_VALUE}
        };
        System.out.println(arrays.findMedianSortedArrays(e[0], e[1]));

        int[][] f = {
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {3, 3, 3, 3}
        };
        System.out.println(arrays.findMedianSortedArrays(f[0], f[1]));
    }
}
