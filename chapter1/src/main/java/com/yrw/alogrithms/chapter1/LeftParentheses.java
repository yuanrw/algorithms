package com.yrw.alogrithms.chapter1;

import java.util.Stack;

/**
 * 输入必须合法
 * 括号内必须是 a符号b 的形式
 * 每个表达式外面都必须有括号包围
 * Date: 2019-03-10
 * Time: 12:28
 *
 * @author yrw
 */
public class LeftParentheses {

    public String leftParentheses(String s) {
        char[] c = s.toCharArray();
        Stack<String> vals = new Stack<>();
        Stack<String> ops = new Stack<>();

        for (int i = 0; i < c.length; i++) {
            String cur = String.valueOf(c[i]);
            if (cur.equals("(")) {
            } else if (cur.equals("+") || cur.equals("-") || cur.equals("*") || cur.equals("/")) {
                ops.push(cur);
            } else if (cur.equals(")")) {
                String v = vals.pop();
                String o = ops.pop();
                String res = addC(vals.pop() + o + v);

                vals.push(res);
            } else {
                vals.push(cur);
            }
        }

        return vals.pop();
    }

    private String addC(String s) {
        return String.format("(%s)", s);
    }

    public static void main(String[] args) {
        LeftParentheses leftParentheses = new LeftParentheses();
        System.out.println(leftParentheses.leftParentheses("1+2)*3-4)*5-6)))"));
        System.out.println(leftParentheses.leftParentheses("1+2)"));
        System.out.println(leftParentheses.leftParentheses("1*2-5))"));
    }

}
