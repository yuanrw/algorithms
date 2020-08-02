package com.yrw.alogrithms.chapter3;

/**
 * Date: 2020/8/1
 * Time: 21:50
 *
 * @author yrw
 */
public class BinaryIndexedTree {

    private final int n;

    /**
     * 维护实时的数组
     */
    private final int[] nums;

    private final int[] binaryTree;

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

    public void add(int i, int delta) {
        i++;
        while (i <= n) {
            binaryTree[i] += delta;
            i += i & -i;
        }
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

        assert tree.sumRange(0, 4) == 32;

        tree.update(4, 1);
        tree.add(3, 1);

        assert tree.sumRange(0, 3) == 27;
        assert tree.sumRange(0, 4) == 28;
    }
}
