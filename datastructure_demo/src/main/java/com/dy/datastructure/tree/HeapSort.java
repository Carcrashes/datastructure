package com.dy.datastructure.tree;

import java.util.Arrays;

/**
 * @author dingyu
 * @description 堆排序
 * @date 2019/12/11
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr={4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 排序
     * @param arr 数组
     */
    public static void heapSort(int[] arr){
        int temp=0;
        //构建一个大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        //1.将堆顶元素与数组末尾元素进行交换。最大值就放置到末尾
        //2.交换值完成之后，反复调整树结构，继续进行堆顶元素与数组末尾元素进行交换
        for(int j=arr.length-1;j>0;j--){
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,j);
        }
    }

    /**
     * 构建大顶堆  左右子节点都小于父节点
     * @param arr 调整数组
     * @param i 数组索引
     * @param length 对应多少个元素进行调整
     */
    public static void adjustHeap(int[] arr,int i,int length){

        int temp=arr[i];//取出当前元素的值，保存临时变量的值

        //k=2*i+1 表示i的左子节点 递增为k*2+1遍历左子节点
        for(int k=2*i+1;k<arr.length;k=k*2+1){
            //k+1 当前结点的右子结点  k+1<length 说明是未越界   arr[k]<arr[k+1] 说明左子结点小于右子结点
            if (k+1<length && arr[k]<arr[k+1]){
                    k++;//指向右子节点

            }
            //子结点大于当前结点信息 子结点与父结点进行交换
            if (arr[k]>temp){
                arr[i]=arr[k];
                i=k;//!!! 将i=k,继续进行循环比较
            }else{
                break;
            }

        }

        //当for循环结束时候，以i为父结点已经构成一个大顶堆，放在了最顶部
        arr[i]=temp;//将temp值放到调整后的位置

    }
}
