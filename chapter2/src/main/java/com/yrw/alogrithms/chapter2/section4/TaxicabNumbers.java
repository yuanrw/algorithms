package com.yrw.alogrithms.chapter2.section4;

/**
 * 1729 = Ta(2) = 1^3+12^3 = 9^3+10^3.
 * Date: 2020/9/11
 * Time: 13:58
 *
 * @author yrw
 */
public class TaxicabNumbers {

    /**
     * 遍历1~n^(1/3)，剩余数字用二分查找来找 O(nlogn)
     *
     * @param n 判断n是否为taxicabNumber
     */
    public void taxiCab(int n) {
        int max = (int) Math.pow(n, 1.0 / 3) + 1;
        for (int i = 0; i < max; i++) {
            double b = n - (Math.pow(i, 3));
            if (b == Math.floor(b) && !Double.isInfinite(b)) {
                int target = (int) b;
                int res = search(i + 1, max, target);
                if (res != -1) {
                    System.out.println(i + " + " + res);
                }
            }
        }
    }

    private int search(int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            long taxi = mid * mid * mid;
            if (taxi == target) {
                return mid;
            } else if (taxi < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        TaxicabNumbers taxicabNumbers = new TaxicabNumbers();
        taxicabNumbers.taxiCab(1729);
    }
}
