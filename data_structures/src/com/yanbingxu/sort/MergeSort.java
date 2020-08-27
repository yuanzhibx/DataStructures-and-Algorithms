package com.yanbingxu.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author Yuanzhibx
 * @Date 2020-08-27
 */
public class MergeSort {

    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[arr.length];
//        mergeSort(arr, 0, arr.length -1, temp);
//        System.out.println("归并排序后 = " + Arrays.toString(arr));

        // 测试归并排序速度  给 80 000 个数据进行测试
        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 8000000; i++) {
            // 生成一个[0, 8000000) 数
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, temp);
        long end = System.currentTimeMillis();
        System.out.println("排序 80 000 数据耗费时间为: " + (int)(end - start) + "ms");
    }

    /**
     * 归并排序 分解 + 合并 方法
     */
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 向左递归分解, 使得左子序列有序
            mergeSort(arr, left, mid, temp);
            // 向右递归分解, 使得右子序列有序
            mergeSort(arr, mid + 1, right, temp);

            // 合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 归并排序合并方法
     *
     * @param arr   要排序的数组
     * @param left  左边有序列表的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 左边有序列表的初始索引
        int i = left;
        // 右边有序列表的初始索引
        int j = mid + 1;
        // temp 的索引
        int t = 0;

        // 先把左右两边(有序)的数据按规则填充到 temp 数组 (直到一边处理完毕)
        while (i <= mid && j <= right) {
            // 左边有序序列的当前元素 ≤ 右边有序序列的当前元素
            if (arr[i] <= arr[j]) {
                // 将左边的当前元素, 拷贝到 temp 数组
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                // 反之, 将右边的当前元素, 拷贝到 temp 数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // 将有剩余数据一边的数据填充到 temp
        while (i <= mid) {
            // 左边的有序序列还有剩余元素
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            // 右边的有序序列还有剩余元素
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // 将 temp 数组的元素拷贝到 arr 数组 (不是每次都拷贝所有)
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
