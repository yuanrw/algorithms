package com.yrw.alogrithms.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 这题和chp4没什么关系，只是放在这里，剑指offer的
 * <p>
 * 实现两个整数大数的加法
 * a,b都可以是正负数或者0
 * Date: 2020/10/2
 * Time: 09:21
 *
 * @author yrw
 */
public class TwoSum {

    public void sum(String a, String b) {
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();

        boolean plus = (aa[0] != '-' && bb[0] != '-')
            || (aa[0] == '-' && bb[0] == '-');

        Deque<Integer> stack = new ArrayDeque<>();

        int p1 = aa.length - 1;
        int p2 = bb.length - 1;

        int carry = 0;

        while (p1 >= 0 && p2 >= 0) {
            if (aa[p1] == '-' || aa[p1] == '+') {
                p1--;
                continue;
            }
            if (bb[p2] == '-' || bb[p2] == '+') {
                p2--;
                continue;
            }

            int aNum = aa[p1] - '0';
            int bNum = bb[p2] - '0';
            int sum = plus ? aNum + bNum : aNum - bNum;
            sum += carry;

            if (sum >= 10) {
                sum %= 10;
                carry = 1;
            } else if (sum < 0) {
                sum *= -1;
                carry = -1;
            } else {
                carry = 0;
            }

            stack.push(sum);

            p1--;
            p2--;
        }

        while (p1 >= 0) {
            if (aa[p1] == '-' || aa[p1] == '+') {
                p1--;
                continue;
            }

            int aNum = aa[p1] - '0';
            int sum = aNum + carry;

            if (sum >= 10) {
                sum %= 10;
                carry = 1;
            } else if (sum < 0) {
                sum *= -1;
                carry = -1;
            } else {
                carry = 0;
            }

            stack.push(sum);

            p1--;
        }

        while (p2 >= 0) {
            if (bb[p2] == '-' || bb[p2] == '+') {
                p2--;
                continue;
            }

            int bNum = bb[p2] - '0';
            int sum = bNum + carry;

            if (sum >= 10) {
                sum %= 10;
                carry = 1;
            } else if (sum < 0) {
                sum *= -1;
                carry = -1;
            } else {
                carry = 0;
            }

            stack.push(sum);

            p2--;
        }

        if (carry == 1) {
            stack.push(1);
        }

        if (carry == -1 || (plus && aa[0] == '-')) {
            System.out.print("-");
        }

        //记得去掉前置0
        boolean first = true;
        while (!stack.isEmpty()) {
            int i = stack.pop();
            if (first && i == 0) {
                if (stack.isEmpty()) {
                    System.out.print("0");
                }
                continue;
            }
            first = false;
            System.out.print(i);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.sum("12", "12");
        twoSum.sum("12343754", "9923222");
        twoSum.sum("0", "-1");
        twoSum.sum("999", "-999");
        twoSum.sum("+3", "970");
        twoSum.sum("99999999999999", "1");
        twoSum.sum("+0", "-0");
    }
}
