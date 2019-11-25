package com.dy.datastructure.search;

/**
 * @author dingyu
 * @description
 * 线性查找算法  数组要求不用是无序的
 * @date 2019/11/19
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr={1,7,8,5,100,19};
        //int i = seqSearch(arr, 0);
        int i = seqSearch(arr, 5);
        if (i<0){
            System.out.println("未查找到数据");
        }else{
            System.out.println("查到数据对应下标为："+i);
        }
    }

    /**
     * 线性查找 满足条件即返回
     * @param arr
     * @param findVal
     * @return
     */
    public static int seqSearch(int[] arr,int findVal){
        if (arr==null){
            return -1;
        }
        int temp=-1;
        for (int i=0;i<arr.length;i++) {
            if (arr[i] == findVal) {
                temp = i;
            }
        }
        return  temp;
    }



}
