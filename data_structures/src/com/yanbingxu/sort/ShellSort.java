package com.yanbingxu.sort;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author Yuanzhibx
 * @Date 2020-08-26
 */
public class ShellSort {

    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort2(arr);

        // 测试选择排序速度 O(n^2)  给 80 000 个数据进行测试
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个[0, 8000000) 数
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        shellSort2(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序 80 000 数据耗费时间为: " + (int) (end - start) + "ms");
    }

    /**
     * 希尔排序方法
     * 交换法
     *
     * @param arr
     */
    private static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共 gap 组), 步长 gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素 大于 加上步长后的元素, 则交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮后: " + Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序方法
     * 移位法
     *
     * @param arr
     */
    private static void shellSort2(int[] arr) {
        int temp = 0;
        // 待插入的数的下标
        int j = 0;
        int count = 0;

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第 gap 个元素逐个对其所在的组直接插入排序
            for (int i = gap; i < arr.length; i++) {
                // 待插入的数的下标
                j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 找到插入位置
                    arr[j] = temp;
                }
            }
        }
    }

}
