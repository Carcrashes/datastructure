package com.dy.datastructure.sort;

import java.util.Arrays;

/**
 * @author dingyu
 * @description
 * 快速排序：
 * 快排思想实际对冒泡排序的一种优化，基本思想将数据分割成为独立两部分数据，其中一部分数据要比另外一部分数据都小，需要找到一个基准数
 * 按照此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行。
 * arr{8,-1,0,-3,4}-->{-1,-3,0,8,4} ->
 * @date 2019/11/6
 */
public class QuickSort {

    public static void main(String[] args) {
            int[] arr={-9,78,0,23,-567,70,-1,900,4561};
            QuickSort.quickSort(arr, 0, arr.length-1 );
            System.out.println("排序后："+ Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int l=left;//左下标
        int r=right;//右下标
        int piovt=arr[(left+right)/2];
        int temp=0; //临时变量 作为交换值使用
        while( l < r){
            //再pivot的左边一直找，找到大于等于pivot的值退出。
            while(arr[l]<piovt){
                l+=1;
            }
            //再pivot的右边一直找，找到小于等于pivot的值退出。
            while(arr[r]>piovt){
                r-=1;
            }
            //l>=r 说明pivot的左右值满足要求，左边全部小于基准值，右边值都小于基准值
            if (l>=r){
                break;//跳出循环
            }

            //交换值
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;

            //注意部分：交换完毕之后arr[l]=pivot相等。l--，否则会死循环
            if (arr[l]==piovt){
                r-=l;
            }

            //交换完毕之后arr[r]=pivot相等。l--，否则会死循环
            if (arr[r]==piovt){
                l+=1;
            }
        }

        if (l==r){
            l+=1;
            r-=1;
        }
        //左递归  //注意左递归开始位置是最左边开始，递归往中间进行，然后结束
        if (left<r){
            quickSort(arr,left,r);
        }
        //右递归  //注意右递归开始位置，都是数组最右端开始递归往中间进行，然后结束
        if (right>l){
            quickSort(arr,l,right);
        }
    }

    public void quickSortDemo(){
        int[] arr={-9,78,0,23,-567,70};
        //基本思想 找到一个基准值 将数组分割成为两部分
        //假如以0 为基准
        //arr 分割为{-9，-567} {78 ，23，70} 分割后只是说明左边比右边小，但是不说明左右两边是有序的
        //按照思想进行分别向左递归或者向右递归。
        //左边递归{-567,-9}  右边递归：{23，70，78}

    }
}
