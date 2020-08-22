package com.yanbingxu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器 & 中缀表达式转换为后缀表达式
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

        System.out.println("-----------------------");

        /*
            完成将一个中缀表达式转成后缀表达式的功能
            1+((2+3)×4)-5  -->  1 2 3 + 4 × + 5 –
            说明:
            1. 因为直接对 str 进行操作不方便，因此 先将 "1+((2+3)×4)-5" 中缀表达式 --> 对应的 List
                即 "1+((2+3)×4)-5" --> ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
            2. 将得到的中缀表达式对应的 List --> 后缀表达式对应的 List
                即 ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] --> ArrayList [1, 2, 3, +, 4, *, +, 5, –]
         */
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("后缀表达式对应的 List" + infixExpressionList);
        List<String> parseSuffixExpresionList = parseSuffixExpresionList(infixExpressionList);
        System.out.println("后缀表达式对应的 List" + parseSuffixExpresionList);

        System.out.printf("expression = %d", calculate(parseSuffixExpresionList));
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
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 将中缀表达式转为对应的 List
     *
     * @param s 中缀表达式 --> 1+((2+3)×4)-5
     * @return
     */
    public static List<String> toInfixExpressionList(String s) {
        // 存放中缀表达式对应的内容
        List<String> list = new ArrayList<>();
        // 指针, 用于遍历中缀表达式字符串
        int i = 0;
        // 用于对多位数的拼接
        String str;
        // 遍历一个字符, 就放入 c 中
        char c;
        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                // c 为非数字, 则加入 list 中
                list.add("" + c);
                i++;
            } else {
                // 如果为数, 则考虑多位数情况
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    // 拼接
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    /**
     * 将中缀表达式对应的 List 转为后缀表达式对应的 List
     */
    public static List<String> parseSuffixExpresionList(List<String> ls) {
        // 运算符栈
        Stack<String> s1 = new Stack<>();
        // 使用 ArrayList 代替存储中间节点栈, 方便操作
//        Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        // 遍历 ls
        for (String item : ls) {
            if (item.matches("\\d+")) {
                // 如果为数, 则加入 s2
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号 ")"，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                // 将 ( 弹出 s1 栈, 消除小括号
                s1.pop();
            } else {
                // item 的优先级 小于等于 s1 栈顶运算符, 将 s1 栈顶的运算符弹出并加入到 s2, 再次转到(4.1)与 s1 中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    // 将 s1 栈顶的运算符弹出并加入到 s2
                    s2.add(s1.pop());
                }
                // 将 item 压入 s1 栈中
                s1.push(item);
            }
        }

        // 将 s1 中剩余的运算符异常弹出并加入到 s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        // 因为存放到 list 中, 所以按顺序输出就是对应的后缀表达式 List
        return s2;
    }

}

/**
 * 可返回 运算符 对应的优先级
 */
class Operation {

    /**
     * 加
     */
    private static int ADD = 1;

    /**
     * 减
     */
    private static int SUB = 1;

    /**
     * 乘
     */
    private static int MUL = 2;

    /**
     * 除
     */
    private static int DIV = 2;

    /**
     * 返回对应的优先级数字
     */
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该预算符");
                break;
        }
        return result;
    }

}
