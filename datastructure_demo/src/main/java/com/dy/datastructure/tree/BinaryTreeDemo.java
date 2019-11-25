package com.dy.datastructure.tree;

/**
 * @author dingyu
 * @description 二叉树前 中 后 序遍历Demo‘
 *   分辨前中后序只需要看父节点：父节点先输出：前序  父节点中间输出：中序  父节点最后输出：后序节点
 * @date 2019/11/21
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        //测试二叉树的前中后序遍历
        //定义树
        BinaryTree binaryTree=new BinaryTree();

        //定义节点信息
        HeroNode node1=new HeroNode(1,"宋江");
        HeroNode node2=new HeroNode(2,"吴用");
        HeroNode node3=new HeroNode(3,"卢俊");
        HeroNode node4=new HeroNode(4,"林冲");
        HeroNode node5=new HeroNode(5,"关胜");

        //设置树节点关系
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(node1);

        //进行前序遍历
       // System.out.println("前序遍历");
       // binaryTree.preOrder();

        //中序遍历
       // System.out.println("中序遍历");
        //binaryTree.infixOrder();

        //后序遍历
        //System.out.println("后序遍历");
        //binaryTree.postOrder();

        //前序查找
        //HeroNode node=null;
        //node = binaryTree.preSearch(5);
       // System.out.println(node);

        //中序查找
        //node = binaryTree.infixSearch(5);
        //System.out.println(node);

        //后序查找
        //node = binaryTree.postSearch(5);
       // System.out.println(node);

        //删除节点
        binaryTree.preOrder();
        //binaryTree.delNodeAndSubtree(5);
        binaryTree.delNode(3);
        System.out.println("---------------------------");
        binaryTree.preOrder();


    }
}

/**
 * 二叉树
 */
class BinaryTree{

    //root节点
    private HeroNode root;

    //设置root节点
    public void setRoot(HeroNode root) {
        this.root = root;
    }
    //前中后序遍历，通过调用节点本身方法进行前中后序遍历
    //前序遍历
    public void preOrder(){
        if (this.root !=null){
            this.root.preOrder();//调用节点本身方法进行递归遍历
        }else{
            System.out.println("二叉树为空，无法进行遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.root !=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法进行遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.root !=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法进行遍历");
        }
    }

    /**
     * 前序查找
     * @param id
     */
    public HeroNode preSearch(Integer id){
        HeroNode node=null;
        if (root !=null){
            node=this.root.preSearch(id);
        }else {
            System.out.println("链表为空");
        }
        return node;
    }


    /**
     * 中序查找
     * @param id
     * @return
     */
    public HeroNode infixSearch(Integer id){
        HeroNode node=null;
        if (root !=null){
            node=root.infixSearch(id);
        }else{
            System.out.println("链表为空");
        }
        return node;
    }

    /**
     * 后序查找
     * @param id
     * @return
     */
    public HeroNode postSearch(Integer id){
        HeroNode node=null;
        if (root !=null){
            node=root.postSearch(id);
        }else{
            System.out.println("链表为空");
        }
        return node;
    }

    /**
     * 删除节点：叶子节点只删除叶子节点，子节点就删除所有子节点为根节点得子树
     * @param id
     */
    public void delNodeAndSubtree(Integer id){
        if (root !=null){
            //如果root节点，匹配当前需要删除节点信息，将root删除，置为空树
            if (root.getId()==id){
                root=null;
            }else{
                //递归查找节点信息
                root.delNodeAndSubtree(id);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }

    /**
     * 删除节点：
     *  叶子节点直接删除叶子节点，
     *  非叶子节点：
     *      只存在一个子节点，则子节点替换当前节点
     *      存在左右两个子节点，则将左子节点替换当前节点，将右子节点置换未左子节点
     * @param id 删除节点id
     */
    public void delNode(Integer id) {
        if (root != null) {
            root.delNode(id);
        } else {
            System.out.println("空树，不能删除");
        }
    }
}

/**
 * 节点
 */
class HeroNode{

    //编码
    private Integer id;

    //名称
    private String name;

    //左节点
    private HeroNode left;

    //右节点
    private HeroNode right;

    public HeroNode(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    //1.先输出父节点信息
    //2.再输出左节点信息。判断子节点的左子节点是否为空，不为空则继续左子树前序遍历
    //3.再输出由节点信息，判断字节的的右子节点是否为空。不为空则进行右子树前序遍历
    public void preOrder(){
        //先输出父节点
        System.out.println(this);
        //左子树遍历
        if (this.left !=null){
            this.left.preOrder();//递归调用进行左子树前序遍历
        }
        //右子树遍历
        if (this.right !=null){
            this.right.preOrder();//递归调用进行右子树前序遍历
        }
    }

    //中序遍历
    //1.先输出左节点信息，判断节点的左子节点是否为空，不为空继续左子树进行中序遍历
    //2.输出父节点信息
    //3.再输出右节点信息，判断节点的右子节点是否为空，不为空继续进行右子树的中序遍历
    public void infixOrder(){
        //左子数递归中序遍历
        if (this.left !=null){
            this.left.infixOrder();
        }
        //输出出父节点
        System.out.println(this);
        //右子树递归进行中序遍历
        if (this.right !=null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    //1.先输出左节点信息，然后进行判断节点的左子节点是否为空，不为空则继续进行左子树的后序遍历
    //2.再输出右节点信息，然后进行判断节点的右字节的是否为空。不为空则进行右子数的后序遍历
    //3.最后输出父节点信息
    public void postOrder(){
        //左子树递归遍历
        if (this.left !=null){
            this.left.postOrder();
        }
        //右子树递归遍历
        if (this.right !=null){
            this.right.postOrder();
        }
        //最后输出父节点信息
        System.out.println(this);
    }

    /***
     * 前序查找
     * @param id
     * @return HeroNode
     */
    public HeroNode preSearch(Integer id){
        //先比较当前节点
        if (this.id==id){
            return this;
        }
        //左子树遍历 找到节点就返回
        HeroNode node=null;
        if (this.left !=null){
            node = this.left.preSearch(id);
        }
        //node !=null 说明在左子树遍历过程中找到了需要找到的节点
        if(node !=null){
            return node;
        }
        //左子树未找到节点，则进行右子树遍历
        if (this.right !=null){
             node = this.right.preSearch(id);
        }
        return node;
    }

    /**
     * 中序查找
     * @param id 查找id
     * @return HeroNode
     */
    public HeroNode infixSearch(Integer id) {
        //先进行左子树遍历查找,找到即返回node
        HeroNode node=null;
        if (this.left !=null){
           node= this.left.infixSearch(id);
        }
        //判断左子树遍历是否找节点
        if (node !=null){
            return node;
        }
        //判断当前节点是否符合查找要求
        if (this.id==id){
            return this;
        }
        //右子树遍历查找，找到返回对应node
        if (this.right !=null){
            node=this.right.infixSearch(id);
        }
        return node;
    }

    /**
     * 后序遍历查找
     * @param id 查找的id
     * @return HeroNode
     */
    public HeroNode postSearch(Integer id){
        //左子树遍历查找，找到即返回
        HeroNode node=null;
        if (this.left !=null){
            node=this.left.postSearch(id);
        }
        //判断左子树遍历，是否找到需要查找的值
        if (node !=null){
            return node;
        }
        //左子树遍历完毕未找到，则判断右子树是否为空，
        if (this.right !=null){
            node=this.right.postSearch(id);
        }
        //判断是否在右子树查找到，查找到即返回node节点
        if (node !=null){
            return node;
        }
        //判断当前节点是否符合查找要求
        if (this.id==id){
            node= this;
        }
        return node;
    }

    //删除节点 规定：如果是叶子节点直接删除叶子节点，如果是节点，直接讲子树删除
    // 思路：
    // 1.首先判断是否是空树，或者删除节点是否是root节点，是root节点直接就删除
    // 2.因为二叉树是单向得，我们不能判断当前节点是否需要删除，我们需要判断当前节点的子节点是否需要删除。
    // 3.如果当前节点的左子节点不为空并且左子节点匹配删除节点id，则进行删除，将this.left=null，结束递归返回
    // 4.左子节点未找到，则判断当前节点的右子节点不为空并且匹配删除节点id,则进行删除，将this.right=null,递归结束返回
    // 5.当前节点左右节点都不匹配，则进行左右子树的遍历（递归方式），找到删除节点即返回，结束递归
    /**
     * 删除节点
     * @param id
     */
    public void delNodeAndSubtree(int id){
        //判断当前节点左子节点是否匹配，匹配即删除
        if (this.left !=null && this.left.id==id){
            this.left=null;
            return;
        }

        //判断当前节点右子节点是否匹配，匹配即删除
        if (this.right !=null && this.right.id==id){
            this.right=null;
            return;
        }

        //进行左子树递归查找，找到匹配值就删除
        if (this.left !=null){
            this.left.delNodeAndSubtree(id);
        }

        //右子树递归查找，找到匹配节点就删除
        if (this.right !=null){
            this.right.delNodeAndSubtree(id);
        }
    }


    /**
     * 删除节点：
     *  1.叶子节点，直接删除
     *  2.非叶子节点：
     *      只有一个子节点，则删除当前节点，并将子节点B替换为当前节点
     *      有两个节点，左子节点B和又子节点C，让左子节点B替换当前节点
     * @param id
     */
    public void delNode(Integer id){
        //判断当前左子节点不为空，并且匹配需要删除节点信息，判断改节点是否有子节点
        //无子节点，则直接删除，若存在一个子节点，将其子节点替换该节点，存在两个节点，将左节点替换为当前节点，并将当前节点右子节点设置为修改后的左子节点
        if (this.left !=null && this.left.id==id){
            if (this.left.left ==null && this.left.right==null){
                this.left=null;
                return;
            }else if(this.left.left !=null && this.left.right !=null){
                HeroNode temp = this.left;
                this.left=this.left.left;
                this.left.left=temp.right;
                return;
            }else if (this.left.left !=null && this.left.right==null){
                this.left=this.left.left;
                return;
            }else if (this.left.left ==null && this.left.right !=null){
                this.left=this.left.right;
                return;
            }
        }

        //判断当前节点的右子节点，且匹配需要删除节点信息，判断改节点是否有子节点
        //无子节点，则直接删除，若存在一个子节点，将其子节点替换该节点，存在两个节点，将左节点替换为当前节点，并将当前节点右子节点设置为修改后的左子节点
        if (this.right !=null && this.right.id==id){
            if (this.right.left ==null && this.right.right==null){
                this.right=null;
                return;
            }else if(this.right.left !=null && this.right.right !=null){
                HeroNode temp = this.right;
                this.right=this.right.left;
                this.right.left=temp.right;
                return;
            }else if (this.right.left !=null && this.right.right==null){
                this.right=this.right.left;
                return;
            }else if (this.right.left ==null && this.right.right !=null){
                this.right=this.right.right;
                return;
            }
        }

        //当前节点未找到，进行左递归遍历
        if (this.left !=null){
            this.left.delNode(id);
        }

        //当前节点未匹配到,进行右递归遍历
        if (this.right !=null){
            this.right.delNode(id);
        }
    }

}