package com.yrw.alogrithms.chapter2;

import com.yrw.algorithms.util.StdRandom;
import com.yrw.alogrithms.chapter2.enums.SortAlgorithm;
import org.apache.commons.lang.time.StopWatch;

/**
 * Date: 2019-07-13
 * Time: 21:35
 *
 * @author yrw
 */
public class SortCompare {

    public static double time(SortAlgorithm sortAlgorithm, Double[] a) {
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
            default:
                throw new RuntimeException("");
        }

        timer.start();

        sort.runTest(a);

        timer.stop();

        return timer.getTime();
    }

    /**
     * 测试排序
     *
     * @param sortAlgorithm 使用算法
     * @param N             数组长度
     * @param T             重复次数
     * @return
     */
    public static double timeRandomInput(SortAlgorithm sortAlgorithm, int N, int T) {
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

    public static void main(String[] args) {
        double t1 = timeRandomInput(SortAlgorithm.SELECT_SORT, 10000, 1);
        double t2 = timeRandomInput(SortAlgorithm.INSERT_SORT, 10000, 1);
        double t3 = timeRandomInput(SortAlgorithm.SHELLS_SORT, 10000, 1);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }
}
