package com.dy.datastructure.sort;

import java.util.Arrays;

/**
 * @author dingyu
 * @description 简单插入排序
 *  存在问题：
 *  arr[2，3，4，5，6，1] 此时进行从小到大排序时候，进行移动次数过多。
 * @date 2019/11/5
 */
public class InsertSort {

    public static void main(String[] args) {
        InsertSort insertSort=new InsertSort();
        //int[] arr={101,34,109,1};
        //int[] ints = insertSort.insertSelect(arr);
        //System.out.println(Arrays.toString(ints));
        insertSort.InsertSortDemo();
    }

    public int[] insertSelect(int[] arr){
        //i=1 表明待插入位置从后移开始
        for(int i=1;i<arr.length;i++){
            //定义待插入
            int insertVal=arr[i];
            int insetIndex=i-1;

            //inserIndex>=0 表明数组不越界
            //while 循环结束标识找到位置
            while (insetIndex>=0 && insertVal<arr[insetIndex]){
                arr[insetIndex+1]=arr[insetIndex];  //将index位置后移
                insetIndex--;
            }

            //判断插入位置是否和i相等
            if (insetIndex+1 !=i){
                arr[insetIndex+1]=insertVal;
            }
        }
        return arr;
    }


    public void InsertSortDemo(){
        int[] arr={101,34,100,1};

        //设置待插入的数字
        int inserVal=arr[1];
        int insertIndex=1-1;
        while (insertIndex>=0 && inserVal<arr[insertIndex]){
            arr[insertIndex+1]=arr[insertIndex];//将位置后移
            insertIndex--;
        }
        arr[insertIndex+1]=inserVal;
        System.out.println("第一次插入:"+Arrays.toString(arr));


        //设置待插入的数字
        inserVal=arr[2];
        insertIndex=1;
        while (insertIndex>=0 && inserVal<arr[insertIndex]){
            arr[insertIndex+1]=arr[insertIndex];//将位置后移
            insertIndex--;
        }
        arr[insertIndex+1]=inserVal;
        System.out.println("第二次插入:"+Arrays.toString(arr));

        //设置待插入的数字
        inserVal=arr[3];
        insertIndex=2;
        while (insertIndex>=0 && inserVal<arr[insertIndex]){
            arr[insertIndex+1]=arr[insertIndex];//将位置后移
            insertIndex--;
        }
        arr[insertIndex+1]=inserVal;
        System.out.println("第三次插入:"+Arrays.toString(arr));
    }


}
