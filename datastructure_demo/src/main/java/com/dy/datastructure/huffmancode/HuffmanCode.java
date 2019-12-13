package com.dy.datastructure.huffmancode;

import javax.xml.soap.Node;
import java.util.*;

/**
 * @author dingyu
 * @description 霍夫曼编码
 * @date 2019/12/13
 */
public class HuffmanCode {

    public static void main(String[] args) {

    }

    /**
     * 编码表
     */
    private static Map<Byte,String> huffmancodes;

    /**
     * 转换为编码
     */
    private static StringBuilder stringBuilder;



    /**
     * 转换为HuNode结点
     * @param bytes
     * @return
     */
    public static List<HuNode> getNodes(byte[] bytes){
        List<HuNode> nodes=new ArrayList<HuNode>();
        //遍历bytes 统计每个byte出现次数->map[key,value]
        Map<Byte,Integer> counts=new HashMap<>();
        for (byte b:bytes){
            Integer count=counts.get(b);
            if (count==null){
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }
        }
        //将每个键值转换为Node对象
        for (Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new HuNode(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    /**
     * 结点信息构建成为霍夫曼树
     * @param nodes
     * @return
     */
    public static HuNode createHuffmanTree(List<HuNode> nodes){
        while (nodes.size()>1){
            //排序
            Collections.sort(nodes);
            //取出最小两个结点
            HuNode left=nodes.get(0);
            HuNode right=nodes.get(1);

            //构建新二叉树
            HuNode parent=new HuNode(null,left.wight+right.wight);// 构建的新的二叉树数据域是没有值
            parent.setLeft(left);
            parent.setRight(right);

            //移除原数组最小两个元素，然后将新构建二叉树加入数组
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        //返回根节点
        return nodes.get(0);
    }

    /**
     * 方便调用进行重载
     * @param root
     * @return
     */
    private static Map<Byte,String> getCodes(HuNode root){
        if (root==null){
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",stringBuilder);
        //处理右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmancodes;
    }


    /**
     * 传入的node结点的所有叶子结点的霍夫曼编码
     * @param node
     * @param code
     * @param stringBuilder
     */
    public static void getCodes(HuNode node,String code,StringBuilder stringBuilder){
        StringBuilder append=new StringBuilder(stringBuilder);
        //将code加入到append
        append.append(code);
        //如果node==null不处理
        if (node !=null){
            //判断当前节点是叶子结点和非叶子结点
            if (node.data==null){ //非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left,"0",append);
                //向右递归
                getCodes(node.right,"1",append);
            }else{
                //说明是一个叶子结点
                //就表示找到某个叶子结点的最后
                huffmancodes.put(node.data,append.toString());
            }

        }

    }
}


/**
 * 结点
 */
class HuNode implements Comparable<HuNode>{

    Byte data;// 存放数据字符本身 比如a=97

    int wight;//权重 表示字符出现次数

    HuNode left;

    HuNode right;

    public HuNode(Byte data, int wight) {
        this.data = data;
        this.wight = wight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public HuNode getLeft() {
        return left;
    }

    public void setLeft(HuNode left) {
        this.left = left;
    }

    public HuNode getRight() {
        return right;
    }

    public void setRight(HuNode right) {
        this.right = right;
    }


    @Override
    public int compareTo(HuNode o) {
        //从小到大排序
        return this.wight-o.wight;
    }

    @Override
    public String toString() {
        return "HuNode{" +
                "data=" + data +
                ", wight=" + wight +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);

        if (this.left !=null){
            this.left.preOrder();
        }

        if (this.right !=null){
            this.right.preOrder();
        }
    }
}
