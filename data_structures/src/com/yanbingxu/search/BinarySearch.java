package com.yanbingxu.search;

/**
 * 二分查找算法
 * 使用二分查找的前提是数组为有序的
 *
 * @author Yuanzhibx
 * @Date 2020-11-16
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};

        int resIndex = binarySearch(arr, 0, arr.length - 1, 123);
        if (resIndex == -1) {
            System.out.println("没有这个数");
        } else {
            System.out.println("resIndex = " + resIndex);
        }
    }

    /**
     * 二分查找方法
     *
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的值
     * @return 找到返回下标, 没找到返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            // 递归完整个数组, 但是没有找到
            return -1;
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findVal > midValue) {
            // 向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {
            // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

}
