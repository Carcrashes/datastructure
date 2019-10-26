package com.dy.datastructure.stack;

/**
 * 数组模拟栈实现
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

    }
}

class ArrayStack{

    private int maxSize; //栈最大存储空间
    private int arr[];//存储栈数组
    private int top=-1;//栈顶

    //初始化
    public ArrayStack(int maxSize){
        this.maxSize=maxSize;
        arr=new int[this.maxSize];
    }

    /**
     * 判断栈空
     * @return
     */
    public boolean isEmpty(){
        return top==-1;
    }

    /**
     * 判断栈满
     * @return
     */
    public boolean isFull(){
        return top==maxSize-1;
    }


    /**
     * 入栈
     * @param value
     */
    public void  push(int value){
        if(isFull()){
            System.out.println("This stack is full");
            return;
        }
        top++;//将栈顶位置后移
        arr[top]=value; //将值赋值给栈顶位置元素
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("this stack is empty ");
        }
        int value=arr[top]; //value 作为临时变量存储栈顶的值
        top--; //将栈顶向前
        return  value;
    }

    /**
     *打印所有栈元素
     */
    public void showAll(){
        if (isEmpty()){
            System.out.println("栈空,无元素");
            return;
        }
        for (int i=top;i<1;i--){
            System.out.printf("stack[%d]=%d",i,arr[i]);
        }
    }
}
