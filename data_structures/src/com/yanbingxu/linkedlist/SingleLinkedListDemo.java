package com.yanbingxu.linkedlist;

/**
 * 单链表
 *
 * @author Yuanzhibx
 * @Date 2020-08-17
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // 创建节点
        HeroNode hero1 = new HeroNode(1, "明凯", "ClearLove");
        HeroNode hero2 = new HeroNode(2, "简自豪", "Uzi");
        HeroNode hero3 = new HeroNode(3, "喻文波", "JackeyLove");
        HeroNode hero4 = new HeroNode(4, "颜丙旭", "Yuanzhibx");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 添加第一种方式, 不考虑编号顺序
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero2);

        // 添加第二种方式, 根据排名将英雄插入到指定位置
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero3);

        // 显示
        singleLinkedList.list();
    }

}

/**
 * StringLinkedList
 * 管理英雄
 */
class SingleLinkedList {

    /**
     * 初始化头结点, 头结点中不存放具体的数据, 头结点不能动
     */
    private final HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表
     * 添加第一种方式, 当不考虑编号顺序时
     * - 1. 找到当前链表的最后节点
     * - 2. 将最后这个节点的 next 指向新的节点
     *
     * @param heroNode 节点
     */
    public void add(HeroNode heroNode) {
        // 因 head 节点不能动, 所以创建一个辅助变量 temp
        HeroNode temp = head;
        // 遍历链表, 找到最后节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 没找到则将 temp 后移
            temp = temp.next;
        }
        // 将最后节点的 next 指向新的节点
        temp.next = heroNode;
    }

    /**
     * 添加节点到单向链表
     * 添加第二种方式, 根据排名将英雄插入到指定位置 (如果有这个排名, 则添加失败, 并给出提示)
     *
     * @param heroNode 节点
     */
    public void addByOrder(HeroNode heroNode) {
        // 创建一个辅助变量 temp 找到新添加节点的位置 (因为是单链表, 所以 temp 是位于添加位置的前一个节点, 否则加入不了)
        HeroNode temp = head;
        // 标识添加的编号是否存在, 默认为 false
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                // temp 在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                // heroNode 位置找到, 在 temp 后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                // heroNode 编号存在
                flag = true;
                break;
            }
            // 后移, 遍历当前链表
            temp = temp.next;
        }

        if (flag) {
            // 不能插入, 说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在, 不能加入\n", heroNode.no);
        } else {
            // 插入到链表中 (temp 后)
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 显示链表[遍历]
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因 head 节点不能动, 所以创建一个辅助变量 temp
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            // 后移 temp
            temp = temp.next;
        }
    }

}

/**
 * HeroNode
 * 每个 HeroNode 对象就是一个节点
 */
class HeroNode {

    /**
     * 编号
     */
    public int no;

    /**
     * 姓名
     */
    public String name;

    /**
     * 昵称
     */
    public String nickname;

    /**
     * 指向下一个节点
     */
    public HeroNode next;

    /**
     * 构造器
     */
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    /**
     * 重写 toString
     */
    @Override
    public String toString() {
        return "HeroNode{no=" + no + ", name='" + name + ", nickname='" + nickname + "}";
    }

}