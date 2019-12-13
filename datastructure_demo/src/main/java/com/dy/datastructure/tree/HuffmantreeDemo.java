package com.dy.datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 霍夫曼树：
 * 1.带权路径长度之和最小  完全二叉树
 * 2.分析构建霍夫曼树
 *  2.1 构建一个可排序结点，从小到大进行排序，然后每个结点可以看成一个最简单二叉树。
 *  2.2 排序完成之后，从顺序存储二叉树数组的中取出最小两个，将权值相加，构建一个新的二叉树结点，作为这个两个数的负结点
 *  2.3 将原来两个结点从数组中移除，然后新的二叉树结加入数组中，然后根据权值重新排序。重复2.2 和 2。3 步骤，当数组中只剩下一个结点时候。构建二叉树完成
 */
public class HuffmantreeDemo {

    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        HuffmanNode root = Huffmantree.createHuffmanTree(arr);
        Huffmantree.preOrder(root);
    }

}

class Huffmantree{

    /**
     * 前序遍历
     * @param root  根结点
     */
    public static void preOrder(HuffmanNode root){
        if (root !=null){
            root.preOrder();
        }else {
            System.out.println("是空树.不能进行遍历");
        }
    }

    public static HuffmanNode createHuffmanTree(int[] arr){
        //构建结点信息
        List<HuffmanNode> nodeList=new ArrayList<HuffmanNode>();
        for (int val:arr){
            HuffmanNode node=new HuffmanNode(val);
            nodeList.add(node);
        }
        //nodeList.size()>1 说明只剩下一个结点了，剩下的结点为root结点信息
        while (nodeList.size()>1){
            //将结点信息进行排序  HuffmanNode 实现了Comparable<T>接口，可以使用Collectios进行排序
            Collections.sort(nodeList);
            //取出权值最小的两个结点，作为子节点，然后将权值相加，作为新二叉树的结点的权，新二叉树作为两个取出结点的父结点
            HuffmanNode left=nodeList.get(0);
            HuffmanNode right=nodeList.get(1);

            //构建新的二叉树
            HuffmanNode parent=new HuffmanNode(left.getValue()+right.getValue());
            parent.setLeft(left);
            parent.setRight(right);

            //将两个最小结点从原来结点中移除，加入新的二叉树结点
            nodeList.remove(left);
            nodeList.remove(right);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }




}




/**
 * 构建结点
 */
class HuffmanNode implements Comparable<HuffmanNode>{

    private int value;

    private HuffmanNode left;

    private HuffmanNode right;

    public HuffmanNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }


    @Override
    public int compareTo(HuffmanNode o) {
        //进行从小到大排序
        return this.value-o.value;
    }

    /**
     * 前序遍历下
     */
    public void preOrder(){

        System.out.println(this.toString());

        //左子树遍历
        if (this.left !=null){
            this.left.preOrder();
        }

        //右子树遍历
        if (this.right !=null){
            this.right.preOrder();
        }

    }
}
