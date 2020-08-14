package com.yrw.alogrithms.chapter2.section3;

/**
 * 在两个排序的数组中找到第K个元素
 * Date: 2020/8/13
 * Time: 21:46
 *
 * @author yrw
 */
public class SelectionInTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double k = (nums1.length + nums2.length - 1) / 2.0 + 1;
        if (k % 1 == 0) {
            return findKth2(nums1, nums2, (int) k);
        } else {
            int a = findKth2(nums1, nums2, (int) k);
            int b = findKth2(nums1, nums2, (int) (k + 0.5));
            return (a + b) / 2.0;
        }
    }

    /**
     * 解法1：双指针，每次移动两个数组中小的那个指针
     *
     * @param a
     * @param b
     * @param k
     * @return
     */
    public int findKth(int[] a, int[] b, int k) {
        int i = 0;
        int j = 0;

        int cnt = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                if (++cnt == k) {
                    return a[i];
                }
                i++;
            } else {
                if (++cnt == k) {
                    return b[j];
                }
                j++;
            }
        }

        //b已经遍历完
        while (i < a.length) {
            if (++cnt == k) {
                return a[i];
            }
            i++;
        }

        //a已经遍历完
        while (j < b.length) {
            if (++cnt == k) {
                return b[j];
            }
            j++;
        }

        return -1;
    }

    /**
     * 解法2：类似二分法，每次取两个数组下标为mid= k/2-1数字
     * 此时有两种情况：
     * 1. 数组合并后，a[mid], b[mid]在最后，此时排除a[0~mid]还是b[0~mid]是无所谓的
     * 2. 数组合并后，a[mid], b[mid]有其中一个在当中，此时必须排除小的那个数字前面的元素
     * 所以每次比较能够排除掉0~mid即mid+1=k/2个数字，时间复杂度为O(logN)
     * 请注意边界值
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int findKth2(int[] nums1, int[] nums2, int k) {
        return findKth2(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, k);
    }

    private int findKth2(int[] a, int aStart, int aEnd, int[] b, int bStart, int bEnd, int k) {
        //判断非法值
        if (k <= 0 || k > a.length + b.length) {
            return -1;
        }
        //如果有一个数组已经找完也没找到,直接返回剩下的那个数组的第k个数
        if (aStart > aEnd) {
            return b[bStart + k - 1];
        } else if (bStart > bEnd) {
            return a[aStart + k - 1];
        }

        if (k == 1) {
            return Math.min(a[aStart], b[bStart]);
        }

        int mid1 = Math.min(aStart + k / 2 - 1, a.length - 1);
        int mid2 = Math.min(bStart + k / 2 - 1, b.length - 1);
        int aLeft = mid1 - aStart;
        int bLeft = mid2 - bStart;
        //要找的数字在右边
        if (aLeft + bLeft <= k) {
            if (a[mid1] <= b[mid2]) {
                //排除a[0~mid1]
                return findKth2(a, aStart + aLeft + 1, aEnd, b, bStart, bEnd, k - aLeft - 1);
            } else {
                //排除
                return findKth2(a, aStart, aEnd, b, bStart + bLeft + 1, bEnd, k - bLeft - 1);
            }
        } else {
            //要找的数组在左边
            if (a[mid1] >= b[mid2]) {
                //排除a[mid1~end]
                return findKth2(a, aStart, mid1 - 1, b, bStart, bEnd, k);
            } else {
                //排除
                return findKth2(a, aStart, aEnd, b, bStart, mid2 - 1, k);
            }
        }
    }

    public static void main(String[] args) {
        SelectionInTwoSortedArrays arrays = new SelectionInTwoSortedArrays();

        int[][] a = {
            {1, 2},
            {-1, 3}
        };
        //2
        System.out.println(arrays.findKth2(a[0], a[1], 3));

        int[][] b = {
            {1, 2, 3},
            {5, 10, 99}
        };
        //10
        System.out.println(arrays.findKth2(b[0], b[1], 5));

        int[][] c = {
            {-999},
            {-999}
        };
        //-999
        System.out.println(arrays.findKth2(c[0], c[1], 1));
        //-999
        System.out.println(arrays.findKth2(c[0], c[1], 2));

        int[][] d = {
            {9, 10, 67, 90, 102},
            {18}
        };
        //18
        System.out.println(arrays.findKth2(d[0], d[1], 3));
        //102
        System.out.println(arrays.findKth2(d[0], d[1], 6));
        //-1
        System.out.println(arrays.findKth2(d[0], d[1], 108));
    }
}
