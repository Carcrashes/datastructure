package com.dy.datastructure.sort;

import java.util.Arrays;

/**
 * @author dingyu
 * @description 希尔排序
 * 缩小增量排序：按照下标进行分组，每组采用直接插入算法。随着增量减少，每组包含关键字越来越少，增量减少到1时候，算法终止。
 *  示例：
 *      arr={8,9 ,1,7,2,3,5,4,6,0}:
 *      length/2 分为
 *      第一次排序:分为5组 ，将下标i,i+5数组分组 例如 8，3 一组，然后比较，arr[1] 与arr[5] 比较 然后小数放置前面，交换位置
 *      第二次排序:增量除2，5/2=2 .将第一次排序分成两组
 *      第三次排序:增量除2 2/2=1 .分为一组数据，然后进行简单排序
 * @date 2019/11/6
 */
public class ShellSort {


    public static void main(String[] args) {
        ShellSort shellSort=new ShellSort();
        //shellSort.shellSortDemo();
        int[] arr={8,9 ,1,7,2,3,5,4,6,0};
        //int[] ints = shellSort.shellSort(arr);
        int[] ints = shellSort.shellSortMove(arr);
        System.out.println(Arrays.toString(ints));

    }

    /**
     * 交换法
     * @param arr
     * @return
     */
    public int[] shellSort(int[] arr) {
        int temp = 0;
        //第一层循环：进行分组，数组长度除以2，既是组数和步长
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //第二层循环：分组遍历，分成几组既是几次
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >=0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
        return arr;
    }


    /**
     * 移动法
     * @param arr
     * @return
     */
    public int[] shellSortMove(int[] arr){
        for (int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，对其所在组进行直接插入排序
            for (int i=gap;i<arr.length;i++ ){
                int j=i;
                int temp=arr[j];
                if (arr[j]<arr[j-gap]){
                    while (j-gap>=0 && temp<arr[j-gap]){
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }


    public void shellSortDemo(){
        int[] arr={8,9 ,1,7,2,3,5,4,6,0};
        int temp=0; //存储临时变量

        //希尔排序
        //外部循环，将数据分成了几组，循环几次排序
        //内部循环,分组数据内部数据排序
        for (int i=5;i<arr.length;i++){
            //分组，组内数据进行比较 数据小的放置前面
            for (int j=i-5;j>=0;j-=5){
                if (arr[j]>arr[j+5]){
                    temp=arr[j];
                    arr[j]=arr[j+5];
                    arr[j+5]=temp;
                }
            }
        }
        System.out.println("第一次希尔排序:"+ Arrays.toString(arr));


        //希尔排序
        for (int i=2;i<arr.length;i++){
            for (int j=i-2;j>=0;j-=2){
                if (arr[j]>arr[j+2]){
                    temp=arr[j];
                    arr[j]=arr[j+2];
                    arr[j+2]=temp;
                }
            }
        }
        System.out.println("第二次希尔排序:"+ Arrays.toString(arr));


        //希尔排序
        for (int i=1;i<arr.length;i++){
            for (int j=i-1;j>=0;j-=1){
                if (arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println("第三次希尔排序:"+ Arrays.toString(arr));
    }



}

