package com.dy.datastructure.binarysorttree;

/**
 * @author dingyu
 * @description 平衡树：平衡树前提是一个二叉排序树
 * @date 2019/12/25
 */
public class AVLTreeDemo {



}

class AVLTree{

    private AVLNode root;

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }

    /**
     * 添加节点
     * @param node 添加节点信息
     */
    public void add(AVLNode node){
        if (root==null){
            root=node; //如果root为空，可以直接将值指向root
        }else{
            root.add(node);
        }
    }


    /**
     * 查找节点
     * @param val 查找结点值
     * @return
     */
    public AVLNode searchNode(int val){
        if (root==null){
            return null;
        }
        return root.searchNode(val);
    }

    /**
     * 查找当前节点的父节点
     * @param val  查找结点值
     * @return
     */
    public AVLNode searchParent(int val){
        if (root ==null){
            return null;
        }
        return root.searchParent(val);
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (root==null){
            System.out.println("这是空树，不能进行遍历");
        } else {
            root.infixOrder();
        }
    }

    /**
     * 查找右子树最新小值得结点并删除
     * @param node 右子树根节点
     * @return
     */
    public int delRightTreeMin(AVLNode node){
        AVLNode targetNode=node;
        //循环遍历查找左子节点
        while (targetNode.getLeft() !=null){
            targetNode=targetNode.getLeft();
        }
        //这时候target指向最小结点
        //删除最小结点
        delBSTNode(targetNode.getValue());
        return targetNode.getValue();
    }

    /**
     * 删除节点
     * @param val 删除节点值
     */
    public void delBSTNode(int val){
        if (root ==null){
            return;
        }

        //先找到删除节点
        AVLNode node = searchNode(val);
        AVLNode parent = searchParent(val);
        //未找到需要删除节点
        if (node==null){
            return;
        }

        /**
         * 当前树只有一个结点，并且找到删除结点信息，说明只有root结点，并且root结点是需要删除结点信息
         */
        if (root.getLeft()==null && root.getRight()==null){
            root=null;
            return;
        }
        if (node.getLeft() ==null && node.getRight() ==null){
            //叶子结点
            if (parent.getLeft() !=null && parent.getLeft().getValue()==val){
                parent.setLeft(null);
            }else if (parent.getRight() !=null && parent.getRight().getValue()==val ){
                parent.setRight(null);
            }
        }else if(node.getLeft() !=null && node.getRight() !=null){
            //有左右子节点
            int minVal = delRightTreeMin(node);
            node.setValue(minVal);
        }else{
            //只有一个子节点
            if (node.getLeft() !=null){
                //有左子节点
                if (parent !=null){
                    if (parent.getLeft() !=null && parent.getLeft().getValue()==val){
                        //删除节点是左子节点
                        parent.setLeft(node.getLeft());
                    }else {
                        parent.setRight(node.getLeft());
                    }
                }else {
                    root=node.getLeft();
                }
            }else{
                //有右子节点
                if (parent !=null){
                    if (parent.getLeft() !=null && parent.getLeft().getValue()==val){
                        parent.setLeft(node.getRight());
                    }else{
                        parent.setRight(node.getRight());
                    }
                }else {
                    root=node.getRight();
                }
            }
        }

    }
}


/**
 * 节点
 */
class AVLNode{

    private int value;

    private AVLNode left;

    private AVLNode right;

    public AVLNode() {
    }

    public AVLNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public void add(AVLNode node){
        if (node==null){
            return;
        }
        if (node.value<this.value){
            //比当前值小，将值添加到左子树
            if (this.left==null){
                this.left=node;
            }else {
                this.left.add(node);
            }
        }else{
            //比当前值大。将值添加到右子树
            if (this.right==null){
                this.right=node;
            }else{
                this.right.add(node);
            }
        }
        //添加完节点之后。判断是否需要进行左右旋转
        if (right.rightHeight()-left.leftHeight()>1){
            //如果当前节点的右子节点为根节点的左子树的高度大于它的右子树的高度 1.先进行右子节点的右子树的右旋转  2.在对当前节点进行左旋转
            if (right !=null && right.leftHeight()>right.rightHeight()){
                //对当前节点的右子节点的为根节点的树进行右旋转
                right.rightRotate();
                //对右子节
                leftRotate();
            }else{
                //否则直接进行左旋转即可
                leftRotate();
            }
        }else if(left.leftHeight()-right.rightHeight()>1){
            //当前节点的左子节点为根节点的树的右子树高度高于它的左子树的高度， 1.对左子节点先进行左旋转 2.对当前节点进行右旋转
            if (left !=null && left.rightHeight()>left.leftHeight()){
                //对当前节点左子树进行左旋转
                left.leftRotate();
                //对当前节点进行右子选择
                rightRotate();

            }else{
                //直接进行右旋转
                rightRotate();
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.left !=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right !=null){
            this.right.infixOrder();
        }
    }

    /**
     * 查找删除的节点
     * @param val
     * @return 返回需要删除的节点
     */
    public AVLNode searchNode(int val){
        if (val==this.value){
            return this;
        } else if(val<this.value){
            if (this.left==null){
                return null;
            }else{
                return this.left.searchNode(val);
            }
        }else{
            if (this.right==null){
                return null;
            }else{
                return this.right.searchNode(val);
            }
        }
    }

    /**
     * 查找删除值得父结点
     * @param val 当前节点
     * @return 返回删除节点父亲节点
     */
    public AVLNode searchParent(int val){
        //如果当前节点就是删除节点得父节点，就返回
        if ((this.left !=null && this.left.value==val) || (this.right !=null && this.right.value==val) ){
            return this;
        }else{
            if (val<this.value && this.left !=null ){
                return this.left.searchParent(val);
            }else if(val >this.value && this.right !=null){
                return this.right.searchParent(val);
            }else{
                return null;
            }
        }
    }

    /**
     * 获取以该节点为根节点的树的高度
     * @return
     */
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    /**
     * 右子树高度
     * @return
     */
    public int rightHeight(){
        if (right==null){
            return 0;
        }
        return right.height();
    }

    /**
     * 左子树高度
     * @return
     */
    public int leftHeight(){
        if (left==null){
            return 0;
        }
        return left.height();
    }


    /**
     * 左旋转
     */
    public void leftRotate(){
        //1.创建一个新的节点
        AVLNode newNode=new AVLNode(value);
        //2.新节点的左子树设置为当前节点的左子树
        newNode.left=left;
        //3.新节点的右子树设置为当前节点的右子树的左子树
        newNode.right=right.left;
        //4.把当前节点的值设置为右节点的值
        value=right.value;
        //5.当前节点的右子树值设置为右子树的右子树
        right=right.right;
        //6.把当前节点左子树设置为当前节点值
        left=newNode;
    }

    /**
     * 右旋转
     */
    public void rightRotate(){
        //1.创建一个新的节点
        AVLNode newNode=new AVLNode(value);
        //2.新节点的右子树指向当前节点右子树
        newNode.right=right;
        //3.新节点的左子树指向当前节点左子树的右子树
        newNode.left=left.right;
        //4.当前节点的值设置为左子节点的值
        value=left.value;
        //5.当前节点的左子树设置为左子树的左子树
        left=left.left;
        //6.当前节点的右子树设置为新节点
        right=newNode;
    }
}



