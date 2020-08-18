package com.yanbingxu.linkedlist;

/**
 * Josephus (约瑟夫) 问题
 *
 * @author Yuanzhibx
 * @Date 2020-08-18
 */
public class Josephus {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        // 加入五个小孩节点
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        // 小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5);
    }

}

/**
 * 单向环形链表
 */
class CircleSingleLinkedList {

    /**
     * first 节点, 第一个, 当前没有编号
     */
    private Boy first = null;

    /**
     * 添加小孩节点, 构建成一个环形的链表
     *
     * @param nums 节点数量
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums 数据不合法");
            return;
        }
        // 辅助指针, 帮助构建环形链表
        Boy curBoy = null;
        // 创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号, 创建节点
            Boy boy = new Boy(i);
            if (i == 1) {
                // 如果是第一个
                first = boy;
                // 构成环
                first.setNext(first);
                // 让 curBoy 指向第一个
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                // 让 curBoy 指向下一个
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历环形链表
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩~~~");
            return;
        }
        // 辅助指针, 帮助构建环形链表
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                // 遍历完毕
                break;
            }
            // curBoy 后移
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入, 计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数不合法, 请重新输入");
            return;
        }
        // 辅助指针, 帮助完成小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                // 说明 helper 指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前, 先让 first 和 helper 移动 k - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 小孩报数时, first 和 helper 指针同时移动 m - 1 次 (循环操作, 直到圈中只有一个节点)
        while (true) {
            if (helper == first) {
                // 圈中只有一个节点
                break;
            }
            // first 和 helper 指针同时移动 countNum - 1 次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩 [%d] 出圈 \n", first.getNo());
            // 将 first 指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩 [%d] \n", helper.getNo());
    }

}

/**
 * Boy 类
 * 表示一个节点
 */
class Boy {

    /**
     * 编号
     */
    private int no;

    /**
     * 指向下一个节点, 默认为 null
     */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
