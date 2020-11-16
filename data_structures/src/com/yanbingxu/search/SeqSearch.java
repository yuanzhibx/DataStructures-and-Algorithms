package com.yanbingxu.search;

/**
 * 线性查找算法
 *
 * @author Yuanzhibx
 * @Date 2020-11-16
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 4396, 443};
        int index = seqSearch(arr, 11);
        if (index == -1) {
            System.out.println("未找到该值");
        } else {
            System.out.println("找到该值, 下标为 [ " + index + " ] 的值正确");
        }
    }

    /**
     * 线性查找方法
     * 这里实现的线性查找为找到一个满足条件的值, 就返回
     *
     * @param arr 数组
     * @param value 要查找的值
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        //逐一比对, 有相同值, 就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
