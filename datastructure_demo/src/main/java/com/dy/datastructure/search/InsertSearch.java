package com.dy.datastructure.search;

/**
 * @author dingyu
 * @description
 * 插值查找算法
 * 要求：数组是有序
 * 思想：类似二分查找算法，不同的是将中间mid查找改为自适应mid处开始查找
 *  使用自适应算法：mid=low+(high-low)*(key-arr[low])/(arr[high]-arr[low])   low 指数组左边索引。 hight指数组右边索引
 * @date 2019/11/19
 */
public class InsertSearch {

    public static void main(String[] args) {
        int[] arr={1,2,4,5,6,10,100,109};
        int i = insertSearch(arr, 0, arr.length-1, 100);
        if (i<0){
            System.out.println("未查找到数据");
        }else{
            System.out.println("查到数据对应下标为："+i);
        }
    }

    public static int insertSearch(int[] arr,int left,int right,int findVal){
        if (arr==null){
            System.out.println("数组不能为空");
        }
        //左边索引大于右边索引，说明递归完整个数组还未找到该值直接返回
        //left>right 说明递归遍历结束 未找到值
        //findVal <arr[0] || findVal>arr[arr.length-1] 说明该值未在数组数值范围内
        if (left>right || findVal <arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }
        int mid=left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal=arr[mid];
        if (findVal>midVal){
            //说明查找值是在数组右边。进行右递归
            return insertSearch(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            //说明查找的值是在数组左边，进行左递归
            return insertSearch(arr,0,mid-1,findVal);
        }else{
            //正好查找该值
            return mid;
        }

    }
}
