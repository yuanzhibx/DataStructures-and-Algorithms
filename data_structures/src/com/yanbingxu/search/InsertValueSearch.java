package com.yanbingxu.search;

/**
 * 插值查找算法
 * 使用插值查找的前提是数组为有序的
 *
 * @author Yuanzhibx
 * @Date 2020-11-16
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println("index = " + index);
    }

    /**
     * 插值查找方法
     *
     * @param arr       数组
     * @param left      左边索引
     * @param right     右边索引
     * @param findValue 查找值
     * @return 找到返回下标, 没找到返回 -1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        System.out.println("插值查找调用");
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }

        // 求出 mid, 自适应
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (findValue > midValue) {
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

}
