package com.dy.datastructure.stack;

/**
 * @author dingyu
 * @description 数组模拟栈
 * @date 2019/10/22
 */
public class ArrayStackDemo {
}

class ArrayStack{
    
    private int maxSize;
    
    private int rear;
    
    private int front;
    
    private int arr[];
    
    public ArrayStack(int maxSize){
        this.maxSize=maxSize;
        arr=new int[this.maxSize];
    }

    public boolean isFull(){
        return  rear==maxSize-1;
    }

    public boolean isEmpty(){
        return rear==-1;
    }

    public void push(){
        if (isFull()){
            System.out.println("stack is full");
        }
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        int value=arr[rear];
        rear--;
        return rear;
    }

    public void showList(){
        if (isEmpty()){
            throw  new RuntimeException("stack is empty");
        }
        for (int i=rear;i<0;i--){
            System.out.printf("stack[%d]=%d",i,arr[i]);
        }
    }
}
