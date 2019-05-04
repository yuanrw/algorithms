package com.yrw.alogrithms.chapter1;

/**
 * 用斐波那契数列实现二分查找
 * Date: 2019-04-08
 * Time: 13:50
 *
 * @author yrw
 */
public class BinarySearchByFibonacci {

    public int binarySearch(int[] array, int target) {
        int[] f = fibonacci(array.length);

        int left = 0, right = array.length;
        int lengthIndex = findIndex(f, right);

        while (left <= right) {
            int f1 = f[lengthIndex - 2];
            int f2 = f[lengthIndex - 1];

            int mid = array[left + f1];
            if (mid == target) {
                return left + f1;
            } else if (mid < target) {
                left += f1 + 1;
                lengthIndex -= 2;
            } else {
                right -= f2;
                lengthIndex -= 1;
            }
        }
        return -1;
    }

    private int findIndex(int[] array, int target) {
        int k = 0;
        while (target > array[k]) {
            k++;
        }
        return k;
    }

    private int[] fibonacci(int max) {
        int[] f = new int[max + 2];
        if (max == 1) {
            f[0] = f[1] = 1;
            return f;
        }
        f[0] = f[1] = 1;
        for (int i = 2; ; i++) {
            f[i] = f[i - 1] + f[i - 2];
            if (f[i - 2] >= max) {
                break;
            }
        }
        return f;
    }

    public static void main(String[] args) {
        BinarySearchByFibonacci binarySearchByFibonacci = new BinarySearchByFibonacci();
        int[] a = {1, 2, 3, 5, 9, 12, 34, 50};
        System.out.println(binarySearchByFibonacci.binarySearch(a, 3));
    }
}
