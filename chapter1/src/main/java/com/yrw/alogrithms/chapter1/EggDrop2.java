package com.yrw.alogrithms.chapter1;

/**
 * 状态：g(i,j)代表用j个蛋尝试i次最多能确定的楼层
 * 动态转移方程：g(i,j)=g(i-1,j-1)+j(i-1,j)+1
 * Date: 2019-10-09
 * Time: 16:52
 *
 * @author yrw
 */
public class EggDrop2 {

    private int dp(int iTime, int j) {
        if (iTime == 1) {
            return 1;
        }
        if (j == 1) {
            return iTime;
        }
        return dp(iTime - 1, j - 1) + dp(iTime - 1, j) + 1;
    }

    public int superEggDrop(int i, int j) {
        int ans = 1;
        while (dp(ans, i) < j) {
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        EggDrop2 eggDrop2 = new EggDrop2();

        System.out.println(eggDrop2.superEggDrop(7, 14));
    }
}
