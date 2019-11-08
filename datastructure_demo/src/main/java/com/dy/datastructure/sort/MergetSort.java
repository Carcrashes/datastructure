package com.dy.datastructure.sort;

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

    }

    /**
     * 归并排序 归+并
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergetSort(int arr[],int left,int right,int[] temp){
            if (left<right){
                int mid=(left+right)/2;
                //向左递归求解
                mergetSort(arr,left,mid,temp);

                //向右递归求解
                mergetSort(arr,mid+1,right,temp);

                //合并
            }
    }

    public static void merget(int[] arr,int left,int mid,int right,int temp){
        int i=left;
        int j=mid+1;

    }

}
