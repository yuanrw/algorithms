package com.yrw.alogrithms.chapter2.section3;

import com.yrw.algorithms.util.StdRandom;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 找到数组中所有出现次数 > n/10的元素
 * > n/10的元素不会超过9个，所以申请9个辅助空间即可
 * Date: 2020/8/15
 * Time: 13:13
 *
 * @author yrw
 */
public class DecimalDominants {

    public Integer[] find(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(9);

        for (int num : nums) {
            Integer n = map.computeIfPresent(num, (k, v) -> v + 1);
            if (n == null) {
                if (map.size() < 9) {
                    map.put(num, 0);
                    continue;
                }

                insert(map, num);
            }
        }

        //验证
        map.replaceAll((k, v) -> 0);
        for (int num : nums) {
            map.putIfAbsent(num, 0);
            map.compute(num, (k, v) -> v + 1);
        }

        return map.entrySet().stream().filter(e -> e.getValue() > nums.length / 10)
            .map(Map.Entry::getKey).toArray(Integer[]::new);
    }

    private void insert(Map<Integer, Integer> map, int num) {
        boolean insert = false;
        while (!insert) {
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                int update = e.getValue() - 1;
                if (update == 1) {
                    map.remove(e.getKey());
                    map.put(num, 1);
                    insert = true;
                } else {
                    map.put(e.getKey(), update);
                }
            }
        }
    }

    public static void main(String[] args) {
        DecimalDominants decimalDominants = new DecimalDominants();

        int[] a = new int[100];
        for (int i = 0; i < 11; i++) {
            a[i] = 2;
        }
        for (int i = 11; i < 23; i++) {
            a[i] = 9;
        }
        for (int i = 23; i < 100; i++) {
            a[i] = 4;
        }
        StdRandom.shuffle(a);

        System.out.println(Arrays.toString(decimalDominants.find(a)));
    }
}
