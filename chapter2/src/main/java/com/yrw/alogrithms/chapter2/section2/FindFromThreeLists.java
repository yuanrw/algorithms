package com.yrw.alogrithms.chapter2.section2;

import java.util.Arrays;
import java.util.List;

/**
 * 2.2.21
 * 三个链表，找相同元素
 * 对链表归并排序，遍历一个链表，对另外两个二分查找。
 * 归并排序=O(nlogn)，二分查找=O(logn)，总=O(3nlogn)~O(logn)
 * 空间复杂度O(n)
 * Date: 2019-12-03
 * Time: 20:30
 *
 * @author yrw
 */
public class FindFromThreeLists {

    public String findName(List<String> list1, List<String> list2, List<String> list3) {
        list1.sort(String::compareTo);
        list2.sort(String::compareTo);
        list3.sort(String::compareTo);

        for (String item : list1) {
            if (list2.contains(item)) {
                if (list3.contains(item)) {
                    return item;
                }
            }

        }

        return null;
    }

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("Alice", "Bob", "Tom");
        List<String> list2 = Arrays.asList("Tom", "Ella", "Tim");
        List<String> list3 = Arrays.asList("Alfie", "John", "Tom");

        FindFromThreeLists findFromThreeLists = new FindFromThreeLists();
        System.out.println(findFromThreeLists.findName(list1, list2, list3));
    }
}
