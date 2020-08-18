package com.yanbingxu.linkedlist;

/**
 * 双向链表
 *
 * @author Yuanzhibx
 * @Date 2020-08-18
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 创建节点
        HeroNode2 hero1 = new HeroNode2(1, "明凯", "ClearLove");
        HeroNode2 hero2 = new HeroNode2(2, "简自豪", "Uzi");
        HeroNode2 hero3 = new HeroNode2(3, "喻文波", "JackeyLove");
        HeroNode2 hero4 = new HeroNode2(4, "颜丙旭", "Yuanzhibx");

        // 创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "YBX", "Yuanzhibx");
        doubleLinkedList.update(newHeroNode);
        System.out.println("-------------修改后的链表情况-------------");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("-------------删除后的链表情况-------------");
        doubleLinkedList.list();

    }

}

/**
 * 双向链表类
 */
class DoubleLinkedList {

    /**
     * 初始化头结点, 头结点中不存放具体的数据, 头结点不能动
     */
    private final HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 返回头结点
     */
    public HeroNode2 getHead() {
        return head;
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
        HeroNode2 temp = head.next;
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

    /**
     * 添加节点到双向链表的最后
     *
     * @param heroNode 节点
     */
    public void add(HeroNode2 heroNode) {
        // 因 head 节点不能动, 所以创建一个辅助变量 temp
        HeroNode2 temp = head;
        // 遍历链表, 找到最后节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 没找到则将 temp 后移
            temp = temp.next;
        }
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 修改节点信息
     * 双向链表的节点内容修改和单向链表一样
     */
    public void update(HeroNode2 newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~~~");
            return;
        }
        // 创建一个辅助变量 temp
        HeroNode2 temp = head.next;
        // 表示是否找到该节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                // 链表遍历完毕
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据 flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            // 没找到该节点
            System.out.printf("没有找到编号 [%d] 的节点, 不能修改~~~", newHeroNode.no);
        }
    }

    /**
     * 删除节点信息
     *      1. 对双向链表, 可以直接找到要删除的节点
     *      2. 找到后, 自我删除既可
     */
    public void del(int no) {
        // 判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空, 无法删除~~~");
        }
        HeroNode2 temp = head.next;
        // 标识是否找到待删除节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                // 到达链表最后
                break;
            }
            if (temp.no == no) {
                // 找到待删除节点的前一个节点 temp
                flag = true;
                break;
            }
            // 后移, 遍历当前链表
            temp = temp.next;
        }
        if (flag) {
//            temp.next = temp.next.next;// 单向链表
            temp.pre.next = temp.next;
            if (temp.next == null) {
                // 如果是最后一个节点, 就不需要执行下句话, 负责出现空指针
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号 [%d] 的节点, 不能修改~~~", no);
        }
    }

}

/**
 * HeroNode
 * 每个 HeroNode 对象就是一个节点
 */
class HeroNode2 {

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
     * 指向下一个节点, 默认为 null
     */
    public HeroNode2 next;

    /**
     * 指向前一个节点, 默认为 null
     */
    public HeroNode2 pre;

    /**
     * 构造器
     */
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    /**
     * 重写 toString
     */
    @Override
    public String toString() {
        return "HeroNode2{no=" + no + ", name='" + name + ", nickname='" + nickname + "}";
    }

}