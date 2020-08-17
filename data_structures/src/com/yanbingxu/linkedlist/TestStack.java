package com.yanbingxu.linkedlist;

import java.util.Stack;

/**
 * 演示栈 Stack 的基本使用
 *
 * @author Yuanzhibx
 * @Date 2020-08-17
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        // 入栈
        stack.add("ClearLove");
        stack.add("Uzi");
        stack.add("JackeyLove");

        // 出栈
        while (stack.size() > 0) {
            // pop(): 将栈顶的数据取出
            System.out.println(stack.pop());
        }
    }

}
