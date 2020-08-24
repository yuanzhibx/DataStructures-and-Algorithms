package com.yanbingxu.recursion;

/**
 * 八皇后问题
 *
 * @author Yuanzhibx
 * @Date 2020-08-22
 */
public class EightQueens {

    /**
     * 表示共有多少个皇后
     */
    static final int MAX = 8;

    /**
     * 保存皇后放置位置的结果
     * 如 arr = {0, 4, 7, 5, 2, 6, 1, 3}
     */
    static int[] array = new int[MAX];

    /**
     * 八皇后问题解法
     */
    static int count = 0;

    /**
     * 判断冲突次数
     */
    static int judgeCount = 0;

    public static void main(String[] args) {
        EightQueens queens = new EightQueens();
        queens.check(0);
        System.out.printf("一共有 %d 解法", count);
        System.out.printf("判断冲突 %d 次", judgeCount);
    }

    /**
     * 将皇后摆放的位置输出
     */
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
        count++;
    }

    /**
     * 查看放置的皇后, 是否和前面已摆放的皇后冲突
     *
     * @param n 放置的第 n 个皇后
     * @return 冲突返回 false   不冲突返回 true
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /*
                说明:
                    1. array[i] == array[n]  表示判断第 n 个皇后是否和前面的 n - 1 个皇后在同一列
                    2. Math.abs(n - i) == Math.abs(array[n] - array[i]  表示判断第 n 个皇后是否和第 i 个皇后在同一斜线
                        - n = 1 array[1] = 1
                        - Math.abs(1-0) == 1
                        - Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
                    3. 判断是否在同一行, 因 n 每次都在递增, 所以不需要判断
             */
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 放置第 n 个皇后
     * 每一次递归时, 进入到 check 都有一个 for (int i = 0; i < MAX; i++), 所以存在回溯
     *
     * @param n 放置的第 n 个皇后
     */
    private void check(int n) {
        if (n == MAX) {
            // n = 8, 则皇后放置完成
            print();
            return;
        }
        // 依次放入皇后, 并判断是否冲突
        for (int i = 0; i < MAX; i++) {
            // 将当前皇后 n 放到该行第 1 列
            array[n] = i;
            // 判断是否冲突
            if (judge(n)) {
                // 不冲突则继续放第 n + 1 个皇后, 开始递归
                check(n + 1);
            }
            // 如果冲突, 就继续执行 array[n] = i; 即第 n 个皇后在本行后移一个位置
        }
    }

}
