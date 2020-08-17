package com.yanbingxu.linkedlist;

import java.util.Stack;

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

        // 修改节点
        HeroNode newHeroNode = new HeroNode(1, "暗凯", "ClearLove7");
        singleLinkedList.update(newHeroNode);
        System.out.println("----------修改后的链表----------");
        singleLinkedList.list();

        // 删除节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        System.out.println("----------删除后的链表----------");
//        singleLinkedList.list();

        // 测试 求单链表中有效节点的个数
        System.out.println("单链表中有效节点的个数" + getLength(singleLinkedList.getHead()));

        // 测试 查找单链表中的倒数第 k 个结点【新浪面试题】
        HeroNode result = findLastIndexNode(singleLinkedList.getHead(), 3);
        System.out.println("result = " + result);

        // 测试 单链表的反转【腾讯面试题，有点难度】
        System.out.println("----------原来链表的情况----------");
        singleLinkedList.list();
//        System.out.println("----------反转单链表----------");
//        reversetList(singleLinkedList.getHead());
//        singleLinkedList.list();

        // 测试 从尾到头打印单链表【百度，要求方式1:反向遍历。方式2:Stack栈】
        System.out.println("----------逆序打印链表----------");
        reversePrint(singleLinkedList.getHead());
    }

    /**
     * 获取单链表中有效节点的个数(如果是带头结点的链表, 则不统计头结点)
     *
     * @param head 链表的头结点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            // 空链表
            return 0;
        }
        int length = 0;
        // 定义一个辅助变量, 不统计头结点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            // 遍历
            cur = cur.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第 k 个结点【新浪面试题】
     * 1. 编写一个方法, 接收 head 节点, 同时接受一个 index
     * 2. index 表示的是倒数第 index 个节点
     * 3. 先把链表从头到尾遍历, 得到链表的总长度 getLength()
     * 4. 得到 size 后, 我们从链表的第一个开始遍历 (size - index) 个, 就可以得到
     * 5. 如果找到, 则返回该节点, 否则返回 null
     *
     * @param head  链表 head 节点
     * @param index 倒数第几个节点
     * @retur
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            // 空链表
            return null;
        }
        // 得到链表的长度 (节点个数)
        int size = getLength(head);
        // 校验 index
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义辅助变量
        HeroNode cur = head.next;
        // 遍历到 size - index 位置，就是倒数的第 K 个节点
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 单链表的反转【腾讯面试题，有点难度】
     *
     * @param head 链表 head 节点
     */
    public static void reversetList(HeroNode head) {
        // 如果当前链表为空或只有一个节点, 则无需反转
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 定义一个辅助的指针(变量), 遍历原来的链表
        HeroNode cur = head.next;
        // 指向当前节点[cur]的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");

        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端
        while (cur != null) {
            // 暂时保存当前节点的下一个节点
            next = cur.next;
            // 将 cur 的下一个节点指向新的链表的最前端
            cur.next = reverseHead.next;
            // 将 cur 连接到新的链表上
            reverseHead.next = cur;
            // 将 cur 后移
            cur = next;
        }
        // 将 head.next 指向 reverseHead.next, 实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 从尾到头打印单链表【百度，要求方式1:反向遍历。方式2:Stack栈】
     * 方式2:Stack栈
     * 可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     * 没有改变链表本身结构
     *
     * @param head 链表 head 节点
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            // 空链表
            return;
        }
        // 创建一个栈, 将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        // 将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            // cur 后移
            cur = cur.next;
        }
        // 将栈中的节点进行打印, pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
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
     * 返回头结点
     */
    public HeroNode getHead() {
        return head;
    }

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
     * 修改节点信息
     * 根据 no 编号来修改, 即 no 编号不能改
     * 根据 newHeroNode 的 no 来修改
     */
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~~~");
            return;
        }
        // 创建一个辅助变量 temp
        HeroNode temp = head.next;
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
     * 1. 定义一个 temp 辅助节点找到待删除节点的前一个节点
     * 2. 比较时, 是 temp.next.no 和需要删除的节点的 no 比较
     */
    public void del(int no) {
        HeroNode temp = head;
        // 标识是否找到待删除节点
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                // 到达链表最后
                break;
            }
            if (temp.next.no == no) {
                // 找到待删除节点的前一个节点 temp
                flag = true;
                break;
            }
            // 后移, 遍历当前链表
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号 [%d] 的节点, 不能修改~~~", no);
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