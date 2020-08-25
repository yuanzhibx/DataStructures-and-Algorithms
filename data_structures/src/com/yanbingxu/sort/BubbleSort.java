package com.yanbingxu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 *
 * @author Yuanzhibx
 * @Date 2020-08-25
 */
public class BubbleSort {

    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//
//        // 测试冒泡排序
//        System.out.println("排序前的数组:  " + Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println("排序后的数组:  " + Arrays.toString(arr));

        // 测试冒泡排序速度 O(n^2)  给 80 000 个数据进行测试
        int[] arr = new int[80000];

        for (int i = 0; i < 80000; i++) {
            // 生成一个[0, 8000000) 数
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序 80 000 数据耗费时间为: " + (int)(end - start) + "ms");
    }

    /**
     * 冒泡排序方法
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        int temp = 0;
        // 标识变量是否进行过交换
        boolean flag = false;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大, 则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                // 在一次排序中, 没有发生过交换
                break;
            } else {
                // 重置 flag, 进行下次判断
                flag = false;
            }
        }
    }

}
