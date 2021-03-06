package com.dy.datastructure.queue;

/**
 * @author dingyu
 * @description 数组模拟队列
 * @date 2019/10/10
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue=new ArrayQueue(3);
        try {
            arrayQueue.addQueue(1);
            arrayQueue.addQueue(2);
            arrayQueue.addQueue(3);
            arrayQueue.showQueue();
            System.out.println(arrayQueue.getQueue());
            arrayQueue.addQueue(4);
            arrayQueue.showQueue();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}

/**
 *  模拟队列
 * @author dingyu
 * @date 2019/10/10
 */
class ArrayQueue{

    private int maxSize;//最大存储数
    private int front=-1;//队列头 （指向队列头部的前一个元素）
    private int rear=-1;//队列尾 （根据下标，这是包含尾部元素）
    private int[] arr;//存储数组数据

    /**
     * 初始化队列
     * @author dingyub
     * @date 2019/10/10
     * @param maxSize
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
     return front==rear;
    }

    /**
     * 判断队列是否满
     * @author dingyu
     * @date 2019/10/10
     * @return boolean
     */
    public boolean isFull(){
        return rear==maxSize-1;
    }

    /**
     * 添加元素
     * @author dingyu
     * @date 2019/10/10
     * @param num
     * @return int[]
     */
    public void addQueue(int num) throws Exception {
        if (isFull()){
            throw new Exception("队列已满,不能添加数据");
        }
        //队列未满可以添加指针
        rear++;//尾部指针向后移一位
        arr[rear]=num;
    }


    /**
     * 取出队列元素
     * @author dingyu
     * @date 2019/10/11
     * @return int
     */
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列数据为空");
        }
        return arr[++front];
    }

    /**
     * 展示队列所有元素
     * @author dingyu
     * @date 2019/10/11
     * @return void
     */
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列数据为空");
            return;
        }
        for (int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    /**
     * 展示头元素
     * @author dingyu
     * @date 2019/10/11
     * @return int
     */
    public int headQueue(){
        if (isEmpty()){
            System.out.println("队列数据为空");
        }
        return arr[front+1];
    }


}
