package com.yanbingxu.search;

import java.util.ArrayList;
import java.util.List;

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

//        int resIndex = binarySearch(arr, 0, arr.length - 1, 123);
//        if (resIndex == -1) {
//            System.out.println("没有这个数");
//        } else {
//            System.out.println("resIndex = " + resIndex);
//        }

        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList = " + resIndexList);

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

    /**
     * 二分查找方法 (二)
     * 当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到
     * <p>
     * 1. 在找到 mid 索引值时，不要马上返回
     * 2. 向 mid 索引值的左边扫描，将所有满足 1000 的元素的下标，加入到集合 ArrayList
     * 3. 向 mid 索引值的右边扫描，将所有满足 1000 的元素的下标，加入到集合 ArrayList
     * 4. 将 Arraylist 返回
     *
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的值
     * @return 找到返回下标, 没找到返回 -1
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            // 递归完整个数组, 但是没有找到
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findVal > midValue) {
            // 向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {
            // 向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            List<Integer> resIndexlist = new ArrayList<>();
            // 向 mid 索引值的左边扫描，将所有满足 1000 的元素的下标，加入到集合 ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                // 将 temp 放入 resIndexlist
                resIndexlist.add(temp);
                temp -= 1;
            }
            resIndexlist.add(mid);
            // 向 mid 索引值的右边扫描，将所有满足 1000 的元素的下标，加入到集合 ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length -1 || arr[temp] != findVal) {
                    break;
                }
                // 将 temp 放入 resIndexlist
                resIndexlist.add(temp);
                temp += 1;
            }
            return resIndexlist;
        }
    }

}
