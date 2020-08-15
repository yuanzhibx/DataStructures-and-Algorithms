package com.yanbingxu.queue;

import java.util.Scanner;

/**
 * 使用环形数组模拟队列
 *
 * @author Yuanzhibx
 * @Date 2020-08-15
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        System.out.println();
        // 创建一个队列, 队列设置为 4, 其队列的有效数据最大是 3
        CircleArray queue = new CircleArray(4);
        // 接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字, 添加到队列");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据时%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

}

/**
 * 数组类
 */
class CircleArray {

    /**
     * 数组的最大容量
     */
    private int maxSize;

    /**
     * 队列头
     * front 变量的含义做一个调整:
     * - front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
     * - front 的初始值 = 0
     */
    private int front;

    /**
     * 队列尾
     * rear 变量的含义做一个调整:
     * - rear 指向队列的最后一个元素的后一个位置, 因为希望空出一个空间做为约定
     * - rear 的初始值 = 0
     */
    private int rear;

    /**
     * 存放数据, 模拟队列
     */
    private int[] arr;

    /**
     * 队列构造器
     */
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否已满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     */
    public void addQueue(int n) {
        // 判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满, 不能加入数据~~~");
            return;
        }
        // 直接将数据加入
        arr[rear] = n;
        // 将 rear 后移, 考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列的数据, 出队列
     */
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空, 不能取数据~~~");
        }
        // 分析出 front 是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        int value = arr[front];
        // 2. 将 front 后移, 考虑取模
        front = (front + 1) % maxSize;
        // 3. 将临时保存的变量返回
        return value;
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列为空, 没有数据~~~");
            return;
        }
        // 从 front 开始遍历, 遍历
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 求出当前队列有效数据的个数
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据, 不是取出数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空, 不能取数据~~~");
        }
        return arr[front];
    }

}