package com.dy.datastructure.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BulleSort {


    public static void main(String[] args) {
            BulleSort bulleSort=new BulleSort();
           bulleSort.bulleSortDemo();//冒泡排序算法步骤演示

            //测试冒泡排序
            int[] arr={3,9,-1,20,-2};
            //arr=bulleSort.bullesort(arr);
        //System.out.println("排序后:"+Arrays.toString(arr));
    }


    public void bulleSortDemo(){
        //冒泡排序  规则 将遍历从下标小开始往大的遍历,然后比较相邻位置值,如果逆序,则交换值.一步步确每个值的位置

        int[] arr={3,9,-1,20,-2};
        int temp=0;
        //第一趟  确定最大的值放到末尾
        for (int i=0;i<arr.length-1-0;i++){
            if (arr[i]>arr[i+1]){
                //进行交交换
                temp=arr[i+1];
                arr[i+1]=arr[i];
                arr[i]=temp;
            }
        }

        System.out.println("第一趟交换:"+ Arrays.toString(arr));

        // 第二趟交换 因为已经确定最大的值了,所以只需要遍历arr.length-1-1的值(去除最后一个数)
        for (int i=0;i<arr.length-1-1;i++){
            if (arr[i]>arr[i+1]){
                //进行交交换
                temp=arr[i+1];
                arr[i+1]=arr[i];
                arr[i]=temp;
            }
        }
        System.out.println("第二趟交换:"+ Arrays.toString(arr));

        // 第三趟交换 因为已经确定最二大的值了,所以只需要遍历arr.length-1-2的值(去除最后两个个数)
        for (int i=0;i<arr.length-1-2;i++){
            if (arr[i]>arr[i+1]){
                //进行交交换
                temp=arr[i+1];
                arr[i+1]=arr[i];
                arr[i]=temp;
            }
        }

        System.out.println("第三趟交换:"+ Arrays.toString(arr));

        // 第四趟交换 因为已经确定最三大的值了,所以只需要遍历arr.length-1-3的值(去除最后两个个数)
        for (int i=0;i<arr.length-1-3;i++){
            if (arr[i]>arr[i+1]){
                //进行交交换
                temp=arr[i+1];
                arr[i+1]=arr[i];
                arr[i]=temp;
            }
        }

        System.out.println("第四趟交换:"+ Arrays.toString(arr));
    }

    /**
     * 简单冒泡排序方法
     * @param arr
     */
    public int[] bullesort(int[] arr){
        int temp=0;

        //优化
        boolean changeFlag=false; //标志位 是否发生了交换
        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    changeFlag=true; //记录是否发生交换
                    temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
            if (!changeFlag){
                break;
            }else{
                changeFlag=false;//重置标志位
            }
        }
        return arr;
    }

}
