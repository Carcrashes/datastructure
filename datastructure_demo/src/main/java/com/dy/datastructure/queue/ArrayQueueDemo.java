package com.dy.datastructure.queue;

/**
 * @author dingyu
 * @description 数组模拟队列
 * @date 2019/10/10
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {

    }
}

/**
 *  模拟队列
 * @author dingyu
 * @date 2019/10/10
 */
class ArrayQueue{

    private int maxSize;//最大存储数
    private int front=-1;//前部指针
    private int rear=-1;//尾部指针
    private int[] arr;//存储数组数据

    /**
     * 初始化队列
     * @author dingyub
     * @date 2019/10/10
     * @param [maxSize]
     * @return
     */
    public ArrayQueue(int maxSize){
            this.maxSize=maxSize;
            this.front=-1;
            this.rear=-1;
            this.arr=new int[maxSize];
    }

    /**
     * 判断是否为空
     * @author dingyu
     * @date 2019/10/10
     * @return boolean
     */
    public boolean isEmpty(){
        if (front==rear){
            return false;
        }
        return true;
    }

    /**
     * 判断队列是否满
     * @author dingyu
     * @date 2019/10/10
     * @return boolean
     */
    public boolean isFull(){
        if (rear==maxSize-1){
            return true;
        }
        return false;
    }

    /**
     * 添加元素
     * @author dingyu
     * @date 2019/10/10
     * @param [num]
     * @return int[]
     */
    public int[] addQueue(int num) throws Exception {
        if (isFull()){
            throw new Exception("队列已满，不能添加数据");
        }
        //队列未满可以添加指针
        rear++;//尾部指针向后移一位
        arr[rear]=num;
        return arr;
    }


}
