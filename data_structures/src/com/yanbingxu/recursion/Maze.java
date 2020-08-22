package com.yanbingxu.recursion;

/**
 * 迷宫问题
 *
 * @author Yuanzhibx
 * @Date 2020-08-22
 */
public class Maze {

    public static void main(String[] args) {
        int[][] map = addMap();
        // 输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("--------------");

        // 使用递归回溯来给小球找路
        setWay(map, 1, 1);
        // 输出新地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * 创建二维数组, 模拟迷宫
     * 使用 1 表示墙
     */
    public static int[][] addMap() {
        // 创建二维数组, 模拟迷宫
        int[][] map = new int[8][7];
        // 上下全部置为 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置为 1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板, 使用 1 表示
        map[3][1] = 1;
        map[3][2] = 1;
        return map;
    }

    /**
     * 使用递归回溯来给小球找路
     * 如果小球能找到 map[6][5], 则说明通路找到
     * 约定: 当 map[i][j]
     *      - 为 0 表示该点没有走过
     *      - 为 1 表示墙
     *      - 为 2 表示通路可以走
     *      - 为 3 表示该点已经走过，但是走不通
     * 在走迷宫时，需要确定一个策略(方法): 下 -> 右 -> 上 -> 左 (如果该点走不通, 再回溯)
     *
     * @param map 表示地图
     * @param i   表示从哪个位置开始出发 (1, 1)
     * @param j   表示从哪个位置开始出发
     * @return 如果找到通路, 返回 true, 否则返回 false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {
                // 该点还没走过, 按照策略走: 下 -> 右 -> 上 -> 左
                // 假定该点可以走通
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    // 向左走
                    return true;
                } else {
                    // 该点走不通, 是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果 map[i][j]!=0 (可能是 1 2 3)
                return false;
            }
        }
    }

}
