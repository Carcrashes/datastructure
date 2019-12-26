package com.dy.datastructure.binarysorttree;

/**
 * @author dingyu
 * @description 二叉排序树
 * 非叶子节点：左子节点小于当前节点 当前节点小于右子节点 ，相同值，即可放置左子节点或者右子节点
 * @date 2019/12/16
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        //测试排序树
        BinarySortTree binarySortTree=new BinarySortTree();
        int[] arr={7,3,10,12,5,1,9};
        for (int num:arr){
            binarySortTree.add(new BSTNode(num));
        }
        //binarySortTree.infixOrder();

        //测试删除
        binarySortTree.delBSTNode(1);
       // binarySortTree.delBSTNode(5);
        //binarySortTree.delBSTNode(10);

        binarySortTree.infixOrder();
    }



}

class BinarySortTree{

    private BSTNode root;

    public BSTNode getRoot() {
        return root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * 添加节点
     * @param node 添加节点信息
     */
    public void add(BSTNode node){
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
    public BSTNode searchNode(int val){
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
    public BSTNode searchParent(int val){
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
    public int delRightTreeMin(BSTNode node){
        BSTNode targetNode=node;
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
        BSTNode node = searchNode(val);
        BSTNode parent = searchParent(val);
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

        /**
         * 删除节点三种情况
         * 1.删除节点是叶子节点
         *  1.1 是父节点的左子节点 parent.left=null
         *  1.2 是父节点的右子节点 parent.right=null
         * 2. 删除节点有一个子节点
         *  2.1 删除节点是它的父节点的左子节点，删除节点有左子节点 parent.left=targetNode.left
         *  2.2 删除节点是它的父节点的左子节点，删除节点有右子节点 parent.left=targetNode.right
         *  2.3 删除节点是它的父节点的右子节点，删除节点有左子节点 parent.right=targetNode.left
         *  2.4 删除节点是它的父节点的右子节点，删除节点有右子节点 parent.right=targetNode.right
         * 3.删除节点有左右子节点
         *   从targetNode的右子树，找到最小的节点，用一个临时遍历保存temp该节点
         *   删除该最小节点
         *   targetNode==temp节点
         * */
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
 * 结点
 */
class BSTNode{

    private int value;


    private BSTNode left;

    private BSTNode right;

    public BSTNode() {
    }

    public BSTNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BSTNode{" +
                "value=" + value +
                '}';
    }

    public void add(BSTNode node){
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
    public BSTNode searchNode(int val){
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
    public BSTNode searchParent(int val){
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

}