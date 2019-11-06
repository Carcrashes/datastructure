package com.dy.datastructure.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 选择排序
 */
public class SelectSort {


    public static void main(String[] args) {
        //测试
        SelectSort selectSort=new SelectSort();
        //selectSort.selectSortDemo();


        int[] arr={5,34,9,108,1};
        arr = selectSort.selectSort(arr);
        System.out.println("排序："+Arrays.toString(arr));
    }

    public  int[] selectSort(int[] arr){
        if (arr==null ||arr.length==1){
            return arr;
        }

        //大循环
        for (int i=0;i<arr.length-1;i++){
            int minVal=arr[i];
            int minIndex=i;
            for (int j=i+1;j<arr.length;j++){
                if (minVal>arr[j]){
                    minIndex=j; //记录最小值坐标
                    minVal=arr[j]; //重置最小值
                }
            }
            if (minIndex !=i){
                arr[minIndex]=arr[i];
                arr[i]=minVal;
            }
        }
        return arr;


    }

    public  void selectSortDemo(){
        int[] arr={101,34,119,1};

        int minVal=arr[0];
        int minIndex=0;
        //第一次排序
        for (int j=0+1;j<arr.length;j++){
            if (minVal>arr[j]){
                minVal=arr[j]; //重置minVal
                minIndex =j; //充值minIndex
            }
        }

        //将最小值放置到arr[0],进行交换
        if (minIndex !=0){
            arr[minIndex]=arr[0];
            arr[0]=minVal;
        }
        System.out.println("第一轮："+Arrays.toString(arr));

        //第二次排序
        minIndex=1;
        minVal=arr[1];
        for (int j=1+1;j<arr.length;j++){
            if (minVal>arr[j]){
                minVal=arr[j];
                minIndex=j;
            }
        }

        if (minIndex !=1){
            arr[minIndex]=arr[1];
            arr[1]=minVal;
        }

        System.out.println("第二轮："+Arrays.toString(arr));
        //第三轮排序
        minIndex=2;
        minVal=arr[2];
        for (int j=2+1;j<arr.length;j++){
            if (minVal>arr[j]){
                //说明假定的值不是最小，获取最小值和最小坐标
                minVal=arr[j];
                minIndex=j;
            }
        }

        //交换值
        if (minIndex !=2){
            arr[minIndex]=arr[2];
            arr[2]=minVal;
        }

        System.out.println("第三轮："+Arrays.toString(arr));

    }

}

