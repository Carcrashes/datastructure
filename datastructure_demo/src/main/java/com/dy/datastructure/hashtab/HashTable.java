package com.dy.datastructure.hashtab;

/**
 * @author dingyu
 * @description
 * 利用哈希表实现保存雇员信息
 * 雇员信息 （id，name）
 * 哈希表数据结构：数组+链表
 *  将id值哈希，然后作为数组的下标值，数组中存储链表，一个数组元素就是一个链表。然后根据id的哈希值将雇员信息存储到数组对应下标的链表中
 *
 *  google上机面试题：当有员工入职时候，要求加入员工信息，然后根据员工id查找该员工信息
 *            要求：不能使用数据库，速度越快越好--》使用哈希表
 *                  使用链表实现哈希表，链表不带表头
 *
 * @date 2019/11/20
 */
public class HashTable {
    //链表数组
    private EmpLinkedList[] linkedListArrays;

    //数组长度 默认为16
    private Integer size=16;

    /**
     * 初始化
     * @param size 初始化长度
     */
    public HashTable(Integer size) {
        this.linkedListArrays = new EmpLinkedList[size];
        //注意点：需要将数组元素的进行初始化
        for (int i=0;i<linkedListArrays.length;i++){
            linkedListArrays[i]=new EmpLinkedList();
        }
    }

    /**
     * 添加雇员信息
     * @param emp
     */
    public void add(EmpNode emp){
        if (emp==null){
           throw new NullPointerException("添加雇员不能为空");
        }
        int hash = hash(emp.getId());
        linkedListArrays[hash].add(emp);
    }

    /**
     * 遍历哈希表
     */
    public void show(){
        for (int i=0;i<linkedListArrays.length;i++){
            linkedListArrays[i].show();
        }
    }

    public EmpNode findEmpById(Integer id){
        if (id==null){
            throw new NullPointerException("id 值不能为空");
        }
        int hash = hash(id);
        EmpNode emp = linkedListArrays[hash].findById(id);
        return  emp;
    }

    //定义散列函数方法
    public int hash(Integer key){
        return key%size;
    }
}


/**
 * 雇员链表信息
 */
class EmpLinkedList{

    private EmpNode head;//头节点 （头节点需要保存的是有数据）

    /**
     * 添加雇员 直接添加节点末尾
     * @param emp 添加的雇员
     */
    public void add(EmpNode emp){
        //判断是否链表为空。链表为空这是第一添加元素，直接将元素赋值给头节点
        if (head==null){
            head=emp;
            return;
        }
        //定义一个辅助节点
        EmpNode curEmp=head;
        //循环结束说明找到节点末尾位置
        while (true){
            //说明到节点末尾
            if (head.next==null){
                break;
            }
            //将辅助节点位置后移
            curEmp=curEmp.next;
        }
        //将节点添加到末尾位置
        curEmp.next=emp;
    }

    /**
     * 按照节点的Id顺序进行添加
     * @param emp 添加的雇员
     */
    public void addOrderById(EmpNode emp){
        //判断是否首次添加元素，链表为空直接将元素赋值给头节点
        if (head==null){
            head=emp;
            return;
        }
        //定义辅助指针节点
        EmpNode curEmp=head;
        while(true){
            if (curEmp.next==null){
                //遍历到链表末尾
                break;
            }
            //遍历查找插入位置节点的前一个位置
            if (curEmp.next.getId()>emp.getId()){
                //找到大于插入值节点的前一个位置
                break;
            }
            //将辅助节点指针后移
            curEmp=curEmp.next;
        }
        //插入值
        EmpNode temp=curEmp.next;
        curEmp.next=emp;
        emp.next=temp;
    }


    /**
     * 显示链表节点信息
     */
    public void show(){
        //遍历节点
        if (head==null){
            System.out.println("节点为空");
        }
        //定义一个辅助节点
        EmpNode curEmp=head;
        while (true){
            if (curEmp.next==null){
                //说明到节点末尾
                break;
            }
            System.out.printf("%d \t %d \n",curEmp.getId(),curEmp.getName());
            //将辅助节点指向下个节点位置
            curEmp=curEmp.next;
        }
    }


    /**
     * 根据雇员Id信息查找雇员信息
     * @param id
     * @return
     */
    public EmpNode findById(Integer id){
        if (id==null){
            return null;
        }
        EmpNode result=null;//定义返回值
        //定义辅助指针变量
        EmpNode curEmp=head;
        while (true){
            if (curEmp.next==null){
                //遍历到链接末尾位置 退出
                break;
            }
            if (id==curEmp.getId()){
                //查找改值
                result=curEmp;
                break;
            }
            //将辅助指针指向下个节点
            curEmp=curEmp.next;
        }
        return  result;
    }
}

/**
 * 雇员节点信息
 */
class EmpNode{

    private Integer id;

    private String name;

    public EmpNode next;

    public EmpNode(Integer id, String name) {
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

    @Override
    public String toString() {
        return "EmpNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
