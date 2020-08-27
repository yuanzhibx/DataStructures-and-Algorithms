package com.yanbingxu.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author Yuanzhibx
 * @Date 2020-08-27
 */
public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

        // 测试选择排序速度  给 80 000 个数据进行测试
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个[0, 8000000) 数
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("排序 80 000 数据耗费时间为: " + (int)(end - start) + "ms");
    }

    /**
     * 快速排序方法
     *
     * @param arr
     */
    private static void quickSort(int[] arr, int left, int right) {
        // 左下标
        int l = left;
        // 右下标
        int r = right;
        // 中轴
        int pivot = arr[(left + right) / 2];
        // 临时变量, 交换时使用
        int temp = 0;

        // 比 pivot 值小的放左边, 大的放右边
        while (l < r) {
            // 在 pivot 左边找 ≥ pivot 的值, 则退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在 pivot 右边找 ≤ pivot 的值, 则退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // pivot 左右两边的值, 已经 左边 ≤ pivot, 右边 ≥ pivot
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果 arr[l] == pivot, 则 r--, 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果 arr[r] == pivot, 则 l--, 后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果 l == r, 必须 l++, r--, 否则出现栈溢出错误
        if (l == r) {
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向左递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

}
