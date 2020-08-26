package com.yanbingxu.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author Yuanzhibx
 * @Date 2020-08-25
 */
public class SelectSort {

    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        System.out.println("排序前: " + Arrays.toString(arr));
//        selectSort(arr);
//        System.out.println("排序后: " + Arrays.toString(arr));

        // 测试选择排序速度 O(n^2)  给 80 000 个数据进行测试
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个[0, 8000000) 数
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序 80 000 数据耗费时间为: " + (int)(end - start) + "ms");
    }

    /**
     * 选择排序方法
     *
     * @param arr
     */
    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    // 说明假定的最小值, 并不是最小
                    // 重置 min
                    min = arr[j];
                    // 重置 minIndex
                    minIndex = j;
                }
            }

            // 交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

}
