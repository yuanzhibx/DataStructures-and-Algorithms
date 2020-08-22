package com.yanbingxu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 *
 * @author Yuanzhibx
 * @Date 2020-08-20
 */
public class PolandNotation {

    public static void main(String[] args) {
        // 定义一个逆波兰表达式  (3+4)×5-6  =>  3 4 + 5 * 6 -   =>  29      (为了方便, 逆波兰表达式数字和符号用空格隔开)
//        String suffixExpression = "3 4 + 5 * 6 - ";
        // 定义一个逆波兰表达式  4 * 5 - 8 + 60 + 8 / 2   =>   4 5 * 8 - 60 + 8 2 / +
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";

        // 1. 先将 "3 4 + 5 × 6 - " => 放到ArrayList中
        // 2. 将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("list = " + list);
        int res = calculate(list);
        System.out.println("计算结果为 = " + res);
    }

    /**
     * 将逆波兰表达式中的数据和运算符, 依次放入 ArrayList 中
     *
     * @param suffixExpression 逆波兰表达式
     * @return
     */
    public static List<String> getListString(String suffixExpression) {
        // 分割 suffixExpression
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 对逆波兰表达式进行运算
     * <p>
     * 1. 从左至右扫描，将 3 和 4 压入堆栈
     * 2. 遇到 + 运算符，因此弹出 4 和 3(4 为栈顶元素，3 为次顶元素)，计算出 3+4 的值，得 7，再将 7 入栈
     * 3. 将 5 入栈
     * 4. 接下来是 × 运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈
     * 5. 将 6 入栈
     * 6. 最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     */
    public static int calculate(List<String> ls) {
        // 创建栈, 一个即可
        Stack<String> stack = new Stack<>();
        // 遍历 ls
        for (String item : ls) {
            // 使用正则表达式取出数 (匹配多位数)
            if (item.matches("\\d+")) {
                // 入栈
                stack.push(item);
            } else {
                // pop 出两个数, 并运算, 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误~~~");
                }
                // 把 res 入栈
                stack.push("" +res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

}
