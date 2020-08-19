package com.yanbingxu.stack;

/**
 * 综合计算器
 *
 * @author Yuanzhibx
 * @Date 2020-08-19
 */
public class Calculator {

    public static void main(String[] args) {
        String expression = "70+20*6-4";
        // 创建数栈
        ArrayStack2 numStack = new ArrayStack2(10);
        // 创建符号栈
        ArrayStack2 operStack = new ArrayStack2(10);
        // 用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        // 将每次扫描得到 char 保存到 ch
        char ch = ' ';
        // 用于拼接多位数
        String keepNum = "";

        // 循环扫描 expression
        while (true) {
            // 依次得到 expression 中的每个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断 ch
            if (operStack.isOper(ch)) {
                // 如果是运算符, 判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        // 如果当前的操作符的优先级小于或者等于栈中的操作符
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 把运算结果入数栈
                        numStack.push(res);
                        // 把当前操作符入符号栈
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符, 就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    // 如果为空, 直接入符号栈
                    operStack.push(ch);
                }
            } else {
                /*
                    需考虑多位数情况, 处理数时, 需要向 expression 的表达式的 index 后再看一位, 如果是数就信息扫描, 如果是符号才入栈
                    因此我们需要定义一个字符串变量, 用于拼接
                 */
                // 处理多位数
                keepNum += ch;

                // 如果 ch 已经是 expression 的最后一位, 就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断下一个字符是不是数字, 如果是数字, 就继续扫描, 如果是运算符, 则入栈 (是看后面一位, 不是 index++)
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 如果后一位是运算符, 则入栈 (keepNum = "1" 或 "123" 转 int)
                        numStack.push(Integer.parseInt(keepNum));
                        // 清空 keepNum
                        keepNum = "";
                    }
                }
            }
            // index + 1 , 判断是否扫描到 expression 最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 当表达式扫描完毕，就顺序的从数栈和符号栈中 pop 出相应的数和符号，并运行
        while (true) {
            // 符号栈为空, 计算结束, 数栈中只有一个数字. 即为结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d ", expression, res2);
    }

}

/**
 * 栈
 * 需要扩展功能: 优先级 数字符号
 */
class ArrayStack2 {

    /**
     * 栈的大小
     */
    private int maxSize;

    /**
     * 数组模拟栈, 数据放在该数组中
     */
    private int[] stack;

    /**
     * 栈顶, 初始化为 -1
     */
    private int top = -1;

    /**
     * 构造器
     */
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈 push
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈 pop
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示栈的情况 [遍历栈], 遍历时, 需要从栈顶开始显示数据
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空, 没有数据~~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级
     * 优先级使用数字表示, 数字越大, 则优先级越高
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            // 假定目前的表达式只有 + - * /
            return -1;
        }
    }

    /**
     * 判断是不是运算符
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     */
    public int cal(int num1, int num2, int oper) {
        // res 用户存放计算的结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                // 注意顺序
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                // 注意顺序
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 返回当前栈顶的值, 看, 不是 pop
     */
    public int peek() {
        return stack[top];
    }

}
