package com.dy.datastructure.sort;

import java.util.Arrays;

/**
 * @author dingyu
 * @description 归并排序
 *  思想：采用分治策略，把问题一步步分解小的问题解决，采用递归方式，小问题解决完成之后就合并
 *  arr={8,4,5,7,1,3,6,2}
 *       分：arr1{8，4，5，7} arr2{1,3,6,2} 继续分arr3{8,4} arr4{5,7} 再进行分arr5{8} arr6{4}，其余分组类似进行
 *       治：奖arr5{8} arr6{4} 进行排序，组成arr7{4,8} 这个合并过程就是治的过程
 * @date 2019/11/7
 */
public class MergetSort {


    public static void main(String[] args) {
        int[] arr={8,4,5,7,1,3,6,2,0};
        int[] temp=new int[arr.length]; //归并排序需要额外的空间
        mergetSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序 归+并
     * @param arr 需要排序数组
     * @param left 数组左边位置
     * @param right 数组右边位置
     * @param temp 临时数组
     */
    public static void mergetSort(int arr[],int left,int right,int[] temp){
            if (left<right){
                int mid=(left+right)/2;//中间索引
                //向左递归求解
                mergetSort(arr,left,mid,temp);
                //向右递归求解
                mergetSort(arr,mid+1,right,temp);
                //合并
                merget(arr,left,mid,right,temp);
            }
    }

    /**
     * 合并的方法
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    public static void merget(int[] arr,int left,int mid,int right,int[] temp){
        System.out.println("合并次数--------");
        int i=left;
        int j=mid+1;
        int t=0; //临时数组的索引

        //把左右数组按照有序序列复制到临时数组中
        //遍历左右数组，将值复制到临时数组中
        while (i<=mid && j<=right){
            //说明左边小于右边，先将左边放到临时数组中
            if (arr[i]<=arr[j]){
                temp[t]=arr[i];
                t+=1; //临时数组索引位置后移
                i+=1; //左边数组索引位置后移
            }else{
                temp[t]=arr[j];
                t+=1; //临时数组索引位置后移
                j+=1; //左边数组索引位置后移
            }
        }

        //将剩余数组中元素复制到临时数组中
        //将左边剩余元素，复制到临时数组中
        while (i<=mid){
            temp[t]=arr[i];
            t+=1;
            i+=1;
        }

        //将右边的元素复制到临时数组中
        while (j<=right){
            temp[t]=arr[j];
            t+=1;
            j+=1;
        }

        //将临时数组赋值到arr中
        //这里复制过程个数不一定是每次arr数组长度。而是将每次分的过程 将其合并时候复制到arr中
        t=0;
        int tempLeft=left;
        while (tempLeft<=right){
            arr[tempLeft]=temp[t];  //注意是将临时数组数组赋值到arr中
            t+=1;
            tempLeft+=1;
        }
    }
}
