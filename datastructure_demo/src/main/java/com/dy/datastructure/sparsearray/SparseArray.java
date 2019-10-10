package com.dy.datastructure.sparsearray;

/**
 * 模拟棋盘 二维数组和稀疏数组相互转换
 * @author dingyu
 * @description
 * @date 2019/10/10
 */
public class SparseArray {

    public static void main(String[] args) {

        //定义一个 长 11  宽 11 的二维数组 默认值为0 白子为1 黑子为2
        int[][] array=new int[11][11];
        //设置值
        array[2][3]=1;
        array[3][4]=2;
        //输出二维数组

        System.out.println("原数组如下所示:");
        int count=0; //记录二维数组不为0数据个数
        for (int i=0;i<array.length;i++){
            for (int j=0; j<array[i].length;j++){
                if(array[i][j] !=0){
                    count++;
                }
                System.out.printf("%d\t",array[i][j]);
            }
            System.out.println();
        }

        //转换为稀疏数组
        //稀疏数组：第1行1列记录原来数组行数 第1行第2列记录原来数组列数  第1行第3列记录非零个数
        //后面依次记录非零的数据行数 列数 值
        int[][] spareArray=new int[count+1][3];
        spareArray[0][0]=11;
        spareArray[0][1]=11;
        spareArray[0][2]=count;

        //遍历原数组
        int sparseCount=0;//用于记录这是第几个非零数据
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[i].length;j++){
                if (array[i][j] !=0){
                    sparseCount++;
                    spareArray[sparseCount][0]=i;//记录数字在原数组的行数
                    spareArray[sparseCount][1]=j;//记录数字在原数组的列数
                    spareArray[sparseCount][2]=array[i][j];//记录原数组值
                }
            }
        }
        System.out.println("转换为稀疏数组如下所示：");
        //遍历打印稀疏数组
        for (int i=0;i<spareArray.length;i++){
            System.out.printf("%d\t%d\t%d\t",spareArray[i][0],spareArray[i][1],spareArray[i][2]);
            System.out.println();
        }

        //稀疏数组 还原为二维数组
        //1.先获取原数组的长和宽
        //2.遍历稀疏数组 获取原数组的行和列 以及值进行赋值
        int[][] oldArray=new int[spareArray[0][0]][spareArray[0][1]];
        for (int i=1;i<spareArray.length;i++){
            oldArray[spareArray[i][0]][spareArray[i][1]]=spareArray[i][2];
        }

        //输出还原的数组
        System.out.println("稀疏数组转为二维数组如下所示:");
        for (int i=0 ;i<oldArray.length;i++){
            for (int j=0;j<oldArray[i].length;j++){
                System.out.printf("%d\t",oldArray[i][j]);
            }
            System.out.println();
        }
    }
}
