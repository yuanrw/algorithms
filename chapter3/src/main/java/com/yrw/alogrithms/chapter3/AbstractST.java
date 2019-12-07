package com.yrw.alogrithms.chapter3;

/**
 * Date: 2019-12-07
 * Time: 23:09
 *
 * @author yrw
 */
public abstract class AbstractST implements ST<Integer, String> {

    protected void test() {
        for (int i = 0; i < 1000; i++) {
            put(i, i + "");
        }

        for (int i = 0; i < 1000; i++) {
            if (!(i + "").equals(get(i))) {
                System.out.println("key: " + i);
                System.out.println("value: " + get(i));
                throw new RuntimeException("put wrong");
            }
        }
    }

}
