package com.yanbingxu.sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author Yuanzhibx
 * @Date 2020-08-27
 */
public class RadixSort {

    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);

        // 测试归并排序速度  给 80 000 个数据进行测试
        int[] arr = new int[80000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < 80000; i++) {
            // 生成一个[0, 8000000) 数
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序 80 000 数据耗费时间为: " + (int)(end - start) + "ms");
    }

    /**
     * 基数排序方法
     *
     * @param arr
     */
    private static void radixSort(int[] arr) {
        // 表示 10 个桶, 每个桶为一个一维数组 (为防止数据溢出, 则每个一维数组为arr.length)
        int[][] bucket = new int[10][arr.length];
        // 记录每个桶中, 实际存放了多少数据 (每个下标记录的是 bucket 对应的桶内数据)
        int[] bucketElementCounts = new int[10];
        // 得到数组中最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的 j 位进行排序处理 (个十百千万...)
                int digitOfElement = arr[j] / n % 10;
                // 放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            // 遍历每一个桶, 并将桶中的数据放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有数据, 则放入原数组
                if (bucketElementCounts[k] != 0) {
                    // 循环第 k 个桶内数据, 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                // 将 bucketElementCounts 置零
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第 " + (i + 1) + " 轮对个位数的排序处理 arr = " + Arrays.toString(arr));
        }
    }

}
