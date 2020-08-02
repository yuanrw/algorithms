package com.yrw.alogrithms.chapter3;

/**
 * Date: 2020/8/1
 * Time: 21:50
 *
 * @author yrw
 */
public class BinaryIndexedTree {

    private int n;
    //维护实时的数组
    private int[] nums;
    private int[] binaryTree;

    public BinaryIndexedTree(int[] nums) {
        this.nums = nums;
        n = nums.length;
        binaryTree = new int[n + 1];

        //update everyone
        for (int i = 1; i <= n; i++) {
            int idx = i;
            int delta = nums[i - 1];
            while (idx <= n) {
                binaryTree[idx] += delta;
                idx += (idx & -idx);
            }
        }
        System.out.println("over");
    }

    public void update(int i, int val) {
        int updateIdx = i;
        int delta = (val - nums[i]);
        i++;
        while (i <= n) {
            binaryTree[i] += delta;
            i += i & -i;
        }
        nums[updateIdx] = val;
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
            return prefix(j);
        } else {
            return prefix(j) - prefix(i - 1);
        }
    }

    //sum of 1~i
    private int prefix(int i) {
        i++;
        // Computes prefix sum of up to the element at index idx
        int result = 0;
        while (i > 0) {
            result += binaryTree[i];
            i -= i & -i;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 7, 2, 0};
        BinaryIndexedTree tree = new BinaryIndexedTree(nums);

        tree.update(4, 6);
        tree.update(0, 2);
        tree.update(0, 9);
        tree.update(3, 8);

        //32
        System.out.println(tree.sumRange(0, 4));

        tree.update(4, 1);

        //26
        System.out.println(tree.sumRange(0, 3));
        //27
        System.out.println(tree.sumRange(0,4));

//        tree.update(1, 1);
//        int[] nums = {1, 3, 5, 0, -2, 100};
//        BinaryIndexedTree tree = new BinaryIndexedTree(nums);

        //9
//        System.out.println(tree.sumRange(0, 2));
//        98
//        System.out.println(tree.sumRange(3, 5));
//        106
//        System.out.println(tree.sumRange(1, 5));
//
//        tree.update(1, 2);
//        tree.update(2, 10);
//
//        1
//        System.out.println(tree.sumRange(0, 0));
//        13
//        System.out.println(tree.sumRange(0, 2));
//        98
//        System.out.println(tree.sumRange(3, 5));
//        110
//        System.out.println(tree.sumRange(1, 5));
    }
}
