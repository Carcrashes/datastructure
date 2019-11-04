package com.dy.datastructure.recursion;

/**
 * 递归 解决迷宫回溯问题
 */
public class MiGong {

    public static void main(String[] args) {
        //进行测试 创建表一个8*7的二维数组表示迷宫.四周为墙都为1 3行 2,3列为墙
        int [][] map=new int[8][7];
        //设置左右墙
        for (int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置上下墙
        for (int j=0;j<7;j++){
            map[0][j]=1;
            map[7][j]=1;
        }

        //设置 3行 2列 和3 列位置为1
        map[3][1]=1;
        map[3][2]=1;
        //输出初始化迷宫
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }


        //为小球找路在迷宫中
        //setWay(map,1,1);  //策略1
        setWay2(map,1,1) ;//策略2
        System.out.println("出路之后的迷宫:-----------------------------------");
        //输出早球之后迷宫
        //输出初始化迷迷宫
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     *  定义一个地图
     *  找到map[5][6] 位置 说明找到终点
     *  约定:map[i][j] 为0 这该点没有走过,当为1 这表示墙 2 表示通路,3 表示该点已经走过
     *  制定策略为 下->右->上->左
     * @param map  二维数组表示地图
     * @param i    起始位置行
     * @param j    起始位置 列
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5]==2){
            //表示找到通路 返回true;
            return true;
        }else {
            if (map[i][j] == 0) {
                //表示这条路还未走过 尝试按照策略进行
                //先假设这条路是通的
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    //向下走 判断是否是通的,通的返回true
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    //向右走
                    return true;
                } else if (setWay(map, i-1, j)) {
                    //向上走
                    return true;
                } else if (setWay(map, i, j-1 )) {
                    //向左走
                    return true;
                } else {
                    //说明这条路走不通
                    //将其值设置为3
                    map[i][j] = 3;
                    return false;
                }

            } else {
                //如果map[i][j] !=0 可能是1 2 3
                return false;
            }
        }
    }

    /**
     *  定义一个地图
     *  找到map[5][6] 位置 说明找到终点
     *  约定:map[i][j] 为0 这该点没有走过,当为1 这表示墙 2 表示通路,3 表示该点已经走过
     *  制定策略为 右->下->上->左
     * @param map  二维数组表示地图
     * @param i    起始位置行
     * @param j    起始位置 列
     * @return
     */
    public static boolean setWay2(int[][] map,int i,int j){
        if (map[6][5]==2){
            //表示找到通路 返回true;
            return true;
        }else {
            if (map[i][j] == 0) {
                //表示这条路还未走过 尝试按照策略进行
                //先假设这条路是通的
                map[i][j] = 2;
                if (setWay(map, i , j+1)) {
                    //向右走 判断是否是通的,通的返回true
                    return true;
                } else if (setWay(map, i-1, j)) {
                    //向右走
                    return true;
                } else if (setWay(map, i-1, j)) {
                    //向上走
                    return true;
                } else if (setWay(map, i, j-1 )) {
                    //向左走
                    return true;
                } else {
                    //说明这条路走不通
                    //将其值设置为3
                    map[i][j] = 3;
                    return false;
                }

            } else {
                //如果map[i][j] !=0 可能是1 2 3
                return false;
            }
        }
    }
}
