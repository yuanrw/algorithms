package com.yrw.alogrithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列去重
 * Date: 2020/9/23
 * Time: 13:27
 *
 * @author yrw
 */
public class Permutations2 {

    boolean[] marked;
    int count;

    public int per(int a, int b, int c, int d) {
        count = 0;
        int[] n = {a, b, c, d};
        marked = new boolean[n.length];

        Arrays.sort(n);
        List<Integer> list = new ArrayList<>();
        dfs(n, 0, list);

        return count;
    }

    private void dfs(int[] n, int start, List<Integer> list) {
        if (start > n.length - 1) {
            if (check(list)) {
                count++;
            }
            return;
        }

        for (int i = 0; i < n.length; i++) {
            if (marked[i]) {
                continue;
            }
            if (i > 0 && n[i] == n[i - 1] && marked[i - 1]) {
                continue;
            }
            list.add(n[i]);
            marked[i] = true;
            dfs(n, start + 1, list);
            list.remove(list.size() - 1);
            marked[i] = false;
        }
    }

    private boolean check(List<Integer> list) {
        int hours = list.get(0) * 10 + list.get(1);
        int min = list.get(2) * 10 + list.get(3);

        return hours < 24 && min < 60;
    }

    public static void main(String[] args) {
        Permutations2 solution = new Permutations2();
        System.out.println(solution.per(1, 0, 0, 2));
        System.out.println(solution.per(2, 1, 2, 1));
        System.out.println(solution.per(1, 4, 8, 2));
        System.out.println(solution.per(4, 4, 4, 4));

        System.out.println(solution.per(0, 0, 0, 0));
        System.out.println(solution.per(9, 8, 7, 6));
    }
}
