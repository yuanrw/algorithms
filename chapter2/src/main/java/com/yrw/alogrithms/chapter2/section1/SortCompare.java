package com.yrw.alogrithms.chapter2.section1;

import com.yrw.algorithms.util.StdRandom;
import com.yrw.alogrithms.chapter2.enums.SortAlgorithm;
import com.yrw.alogrithms.chapter2.section2.MergeSort;
import org.apache.commons.lang.time.StopWatch;

/**
 * Date: 2019-07-13
 * Time: 21:35
 *
 * @author yrw
 */
public class SortCompare {

    private static double time(SortAlgorithm sortAlgorithm, Double[] a) {
        StopWatch timer = new StopWatch();
        AbstractSort<Double> sort;
        switch (sortAlgorithm) {
            case SELECT_SORT:
                sort = new SelectSort<>();
                break;
            case INSERT_SORT:
                sort = new InsertSort<>();
                break;
            case SHELLS_SORT:
                sort = new ShellsSort<>();
                break;
            case INSERT_SORT_NO_EXCH:
                sort = new InsertionX<>();
                break;
            case MERGE_SORT:
                sort = new MergeSort<>();
                break;
            default:
                throw new RuntimeException("");
        }

        timer.start();

        sort.sort(a);

        timer.stop();

        assert sort.isSorted(a);
        return timer.getTime();
    }

    /**
     * 测试随机输入排序
     *
     * @param sortAlgorithm 使用算法
     * @param N             数组长度
     * @param T             重复次数
     * @return
     */
    private static double timeRandomInput(SortAlgorithm sortAlgorithm, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(sortAlgorithm, a);
        }
        return total;
    }

    /**
     * 2.1.31
     * 双倍测试，用于验证时间复杂度
     */
    private static void doubleTest() {
        System.out.println(SortAlgorithm.SELECT_SORT.toString());
        double previousTime = 1;
        for (int len = 1000; len < 100000; len *= 2) {
            double t = timeRandomInput(SortAlgorithm.SELECT_SORT, len, 1);
            System.out.println(t / previousTime);
            previousTime = t;
        }

        System.out.println(SortAlgorithm.INSERT_SORT);
        for (int len = 1000; len < 100000; len *= 2) {
            double t = timeRandomInput(SortAlgorithm.INSERT_SORT, len, 1);
            System.out.println(t / previousTime);
            previousTime = t;
        }

        System.out.println(SortAlgorithm.INSERT_SORT);
        for (int len = 1000; len < 100000; len *= 2) {
            double t = timeRandomInput(SortAlgorithm.SHELLS_SORT, len, 1);
            System.out.println(t / previousTime);
            previousTime = t;
        }
    }

    public static void main(String[] args) {
        double t1 = timeRandomInput(SortAlgorithm.SELECT_SORT, 10000, 1);
        double t2 = timeRandomInput(SortAlgorithm.INSERT_SORT, 10000, 1);
        double t3 = timeRandomInput(SortAlgorithm.INSERT_SORT_NO_EXCH, 10000, 1);
        double t4 = timeRandomInput(SortAlgorithm.SHELLS_SORT, 10000, 1);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
    }
}
