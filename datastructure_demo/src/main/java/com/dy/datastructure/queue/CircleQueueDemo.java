package com.dy.datastructure.queue;

/**
 * @author dingyu
 * @description 数组模拟队列
 * @date 2019/10/10
 */
public class CircleQueueDemo {
    public static void main(String[] args) {
        CircleQueue circleQueue=new CircleQueue(4); //maxSize为4，最大空间为3
        try {
            circleQueue.addQueue(10);
            circleQueue.addQueue(20);
            circleQueue.addQueue(30);
            circleQueue.showQueue();
            System.out.println(circleQueue.getQueue());
            circleQueue.addQueue(40);
            circleQueue.showQueue();
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
class CircleQueue{

    private int maxSize;//最大存储数
    private int front;//队列头 （指向队列头部元素）
    private int rear;//队列尾 （尾部元素的后一位）
    private int[] arr;//存储数组数据

    /**
     * 初始化队列
     * @author dingyub
     * @date 2019/10/10
     * @param maxSize
     * @return
     */
    public CircleQueue(int maxSize){
            this.maxSize=maxSize;
            this.front=0;
            this.rear=0;
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
        return (rear+1)%maxSize==front;
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
            throw new RuntimeException("队列已满,不能添加数据");
        }
        arr[rear]=num;
        //将队尾指针后移，必须考虑取模（环形数组）
        rear=(rear+1)%maxSize;
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
        //环形队列元素是指向队列头部元素，直接返回front位置元素即可
        int value=arr[front];//将front位置元素赋值给一个临时工变量
        front=(front+1)%maxSize; //取模防止数组越界
        return value;
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
        //思路，从front开始变量，遍历队列中多少个元素即可。
        //求出当前队列的有效数据个数
        int size=size();
        for (int i=front;i<front+size;i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]); //取模防止数组越界
        }
    }

    /**
     * 队列有效数据个数
     * @author dingyu
     * @date 2019/10/11
     * @return int
     */
    public int size(){
        return (rear+maxSize-front)%maxSize;
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
        return arr[front];
    }


}
