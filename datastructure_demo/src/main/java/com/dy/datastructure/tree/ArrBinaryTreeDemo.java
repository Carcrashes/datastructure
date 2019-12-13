package com.dy.datastructure.tree;

/**
 * @author dingyu
 * @description 顺序存储二叉树遍历
 * @date 2019/12/2
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree tree=new ArrBinaryTree(arr);
        //测试顺序存储二叉树前序遍历
        //tree.preOrder();
        //测试顺序存储二叉树中序遍历
        //tree.infixOrder(0);
        //测试顺序存储二叉树后序遍历
        tree.postOrder(0);
    }
}

/**
 * 数组存储二叉树满足条件  n表示二叉树的第几个元素
 * 1.完全二叉树
 * 2.第n个元素的左子节点为2*n+1
 * 3.第n个元素的右子节点为2*n+2
 * 4.第n个元素的父节点为(2-n)/2
 */
class ArrBinaryTree{

    private int[] arr;//存储数据

    public ArrBinaryTree(int[] arr){
        this.arr=arr;
    }

    /**
     * 前序遍历 （方法重载）
     */
    public void preOrder(){
        this.preOrder(0);
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        this.infixOrder(0);
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        this.postOrder(0);
    }

    /**
     * 前序遍历
     * @param index 开始遍历节点索引
     */
    public void preOrder(int index){
        if (arr==null || arr.length==0){
            System.out.println("数组为空，不能进行遍历");
            return;
        }

        //输出当前元素
        System.out.println(arr[index]);

        //左子树遍历
        if (2*index+1<arr.length){
            this.preOrder(2*index+1);
        }

        //右子树遍历
        if (2*index+2<arr.length){
            this.preOrder(2*index+2);
        }
    }

    /**
     * 中序遍历
     * @param index 开始遍历节点索引
     */
    public void infixOrder(int index){
        if (arr==null || arr.length==0){
            System.out.println("数组为空，不能进行遍历");
            return;
        }

        //左子树遍历
        if (2*index+1<arr.length){
            this.infixOrder(2*index+1);
        }

        //输出当前元素
        System.out.println(arr[index]);

        //右子树遍历
        if (2*index+2<arr.length){
            this.infixOrder(2*index+2);
        }
    }


    /**
     * 后序遍历
     * @param index 开始遍历节点索引
     */
    public void postOrder(int index){
        if (arr==null || arr.length==0){
            System.out.println("数组为空，不能进行遍历");
            return;
        }

        //左子树遍历
        if (2*index+1<arr.length){
            this.postOrder(2*index+1);
        }

        //右子树遍历
        if (2*index+2<arr.length){
            this.postOrder(2*index+2);
        }

        //输出当前元素
        System.out.println(arr[index]);
    }

}
