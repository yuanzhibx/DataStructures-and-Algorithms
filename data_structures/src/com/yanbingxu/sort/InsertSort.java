package com.yanbingxu.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author Yuanzhibx
 * @Date 2020-08-26
 */
public class InsertSort {

    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        insertSort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));

        // 测试插入排序速度 O(n^2)  给 80 000 个数据进行测试
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个[0, 8000000) 数
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序 80 000 数据耗费时间为: " + (int)(end - start) + "ms");
    }

    /**
     * 插入排序方法
     *
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 待插入的数
            int insertVal = arr[i];
            // 待插入数的前一个数下标
            int insertIndex = i - 1;

            /*
                给 insertVal 找到插入的位置
                    insertIndex >= 0 保证在给 insertVal 找插入位置时不越界
                    insertVal < arr[insertIndex] 待插入的数还没有找到插入位置
             */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // arr[insertIndex] 后移
                arr[insertIndex + 1] = arr[insertIndex];
                // 向前比较, >= 0 则退出
                insertIndex--;
            }

            if (insertIndex + 1 != i) {
                // 当退出 while 循环时，说明插入的位置找到, insertIndex + 1
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.println("第" + (i - 1) + "轮插入后：" + Arrays.toString(arr));
        }
    }

}
