package com.yanbingxu.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author Yuanzhibx
 * @Date 2020-08-25
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};

        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大, 则交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第 [" + (i + 1) + "] 次排序后的数组:  " + Arrays.toString(arr));
        }
    }

}
