package com.dy.datastructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingyu
 * @description
 * 二分查找算法
 * 要求：数组是有序的
 * 思想：确定中间下标位置mid，然后将需要查找的值findVal与arr[mid]进行比较。如果findVal<arr[mid]，说明值在左边。左递归查找，
 * 如果findVal>arr[mid]，说明查找的值在右边，然后右递归查找。直到查找该值则返回，否则返回未查找到
 * @date 2019/11/19
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr={1,2,4,5,6,10,10,100,109};
        List<Integer> integers = binarySearchList(arr, 0, arr.length-1, 10);
        System.out.println(integers);

    }

    /**
     * 二分查找算法 找到即返回
     * @param arr 查找数组
     * @param left 数组左边索引
     * @param right 数组右边索引
     * @param findVal 查找的值
     * @return
     */
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        if (arr==null){
            System.out.println("数组不能为空");
        }
        //左边索引大于右边索引，说明递归完整个数组还未找到该值直接返回
        if (left>right){
            return -1;
        }
        int mid=(left+right)/2;
        int midVal=arr[mid];
        if (findVal>midVal){
            //说明查找值是在数组右边。进行右递归
           return binarySearch(arr,mid+1,right,findVal);
        }else if (findVal<midVal){
            //说明查找的值是在数组左边，进行左递归
            return binarySearch(arr,0,mid-1,findVal);
        }else{
            //正好查找该值
            return mid;
        }
    }

    /**
     * 查找到所有匹配值才返回
     * @param arr 数组
     * @param left 数组左边索引
     * @param right 数组右边索引
     * @param findVal 查找的值
     * @return
     */
    public static List<Integer> binarySearchList(int[] arr,int left,int right,int findVal){
        if (null == arr){
            return null;
        }
        int mid=(left+right)/2;
        int midVal=arr[mid];
        if (findVal<midVal){
            return binarySearchList(arr,0,mid-1,findVal);
        }else if (findVal>midVal){
            return binarySearchList(arr,mid+1,right,findVal);
        }else {
            List<Integer> resIndexList = new ArrayList<>();
            //左边扫描遍历，将满足findVal值加入到list集合中
            int temp = mid - 1;
            while (true) {
                //temp<0 防止数组越界  //arr[temp] !=findVal 说明扫描结束还未找到相同值
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp); //添加所有值小标索引
                temp -= 1; //左移
            }
            //右边扫描 将满足findVal值加入到list集合中
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            resIndexList.add(mid);//需要将找到中间值得下标加入到集合中
            return resIndexList;
        }
    }



}
