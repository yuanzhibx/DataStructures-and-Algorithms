package com.yanbingxu.recursion;

/**
 * 递归调用机制
 *
 * @author Yuanzhibx
 * @Date 2020-08-22
 */
public class RecursionTest {

    public static void main(String[] args) {
        // 回顾递归调用机制
        test(4);
        System.out.println("--------------------");
        int res = factorial(3);
        System.out.println("res = " + res);
    }

    /**
     * 打印问题
     * 不加入 else 输出: 2 3 4
     * 加入 else 输出: 2
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n = " + n);
    }

    /**
     * 阶乘问题
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }

}
