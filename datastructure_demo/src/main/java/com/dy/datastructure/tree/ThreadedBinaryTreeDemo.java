package com.dy.datastructure.tree;

/**
 * @author dingyu
 * @description 线索化二叉树
 * @date 2019/12/2
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        Node root=new Node(1,"A");
        Node node1=new Node(3,"B");
        Node node2=new Node(6,"D");
        Node node3=new Node(8,"E");
        Node node4=new Node(10,"F");
        Node node5=new Node(14,"G");

        //手动构建二叉树
        ThreadedBinaryTree threadedBinaryTree=new ThreadedBinaryTree();
        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node4);
        node2.setLeft(node3);
        node3.setLeft(node5);
        threadedBinaryTree.setRoot(root);

        //测试中序线索化
        threadedBinaryTree.infixThreadedNode();

        //测试10号结点前驱和后继节点
        System.out.println("10号节点的前驱节点"+node5.getLeft());
        System.out.println("10号节点的后继节点"+node5.getRight());

        //线索化后不能使用原来的遍历方式，因为节点的左子节点和右子节点方式
        //threadedBinaryTree.threadedList()
    }
}

//线索化二叉树
class ThreadedBinaryTree{

    /**
     * 头节点
     */
    private Node root;

    /**
     * 前置节点  为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
     */
    private Node pre;

    public ThreadedBinaryTree() {
    }

    public ThreadedBinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preThreadedNode(){
        preThreadNodes(root);
    }


    /**
     * 重载 中序方式线索化二叉树
     */
    public void infixThreadedNode(){
        infixThreadedNodes(root);
    }

    /**
     * 重载 后序方式线索化二叉树
     */
    public void postThreaedNode(){
        postThreadedNode(root);
    }
    /**
     * 遍历线索化二叉树
     */
    public void threadedList(){
        //定义一个变量，存储当前遍历的节点信息，从root开始
        Node node=root;
        while (node !=null){
            //循环的找到leftType==1的结点，后面随着遍历而变化，因为当leftType==1时候，说明该结点是按照线索化的
            //处理后的有效节点
            while (node.getLeftType() ==0){
                node=node.getLeft();
            }
            //打印当前节点
            System.out.println(node);

            //如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType()==1){
                //获取当前结点的后继结点
                node=node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node=node.getRight();
        }
    }

    /**
     * 前序线索化二叉树
     * @param node
     */
    public void preThreadNodes(Node node){
        if (node==null){
            return;
        }
        //先线索化当前节点
        if (node.getLeft()==null){
            //设置前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理前驱节点的后继节点
        if (pre !=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //!!! 处理完成之后，需要将当前节点作为下个节点的前驱节点
        pre=node;

        //线索化左子树
        preThreadNodes(node.getLeft());

        //线索化右子树
        preThreadNodes(node.getRight());
    }


    /**
     * 中序线索二叉树
     * @param node 需要线索化的结点
     */
    public  void infixThreadedNodes(Node node){
        if (node==null){
            return;
        }
        //先线索化左子树
        infixThreadedNodes(node.getLeft());
        //线索化当前结点 如果当前结点left==null,说明左子节点可以指向前驱节点，设置leftType为1
        if (node.getLeft()==null){
            //让当前结点的左指针指向前驱节点
            node.setLeft(pre);
            //设置左子数类型为1
            node.setLeftType(1);
        }

        //处理前驱结点的后继节点
        if (pre !=null && pre.getRight() ==null){
            //让前驱节点右指针指向当前结点
            pre.setRight(node);
            //修改后继节点的指向类型
            pre.setRightType(1);
        }
        //!!! 注意每处理完一个节点，让当前结点作为下一个结点的前驱结点
        pre=node;
        //线索化右子树
        infixThreadedNodes(node.getRight());
    }


    /**
     * 后序线索化二叉树
     * @param node
     */
    public void postThreadedNode(Node node){
        if (node==null){
            return;
        }
        //线索化左子树
        postThreadedNode(node.getLeft());
        //线索化右子树
        postThreadedNode(node.getRight());
        //处理当前节点的前驱节点
        if (node.getLeft()==null){
            node.setLeft(node);
            node.setLeftType(1);
        }

        //处理前驱节点的后继节点
        if (pre !=null && pre.getRightType()==0){
            pre.setRight(node);
            pre.setRightType(1);
        }

        //!!! 将让当前节点设置为下一个节点的前驱节点
        pre=node;
    }

}

// 节点信息
class Node{

    private int no;

    private String name;

    private Node left; //左子节点，默认为null

    private Node right;//右子节点，默认为null

    private int leftType;// 左子节点类型，然后左子节点可能指向子节点或者前驱节点 0：表示左子节点 1：表示前驱节点

    private int rightType;//右子节点类型，然后右子节点可能指向子节点或者后驱节点 0：表示右子节点 1：表示后继节点

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}