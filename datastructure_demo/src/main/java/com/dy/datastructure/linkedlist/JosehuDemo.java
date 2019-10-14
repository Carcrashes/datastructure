package com.dy.datastructure.linkedlist;

/**
 * @author dingyu
 * @description 约瑟夫问题解决：
 * 设置编号 1.2...n的人围坐一圈。约定编号为k的人开始从报数，数到m个人出列，下一位有从一开始报数，数到m那个人又出列，依次类推，
 * 直到列出所有人为止，产生一个出列顺序
 *
 *  解决方式：首先设置无头节点环形单链表
 * @date 2019/10/14
 */
public class JosehuDemo {
}


class JoseHuLinkedList{

    private PersonNode first=null;// 链表第一个节点

    public void addPerson(){
        
    }

}

class PersonNode{
    //编号
    private int no;

    //下一个节点域
    private PersonNode next;

    public PersonNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public PersonNode getNext() {
        return next;
    }

    public void setNext(PersonNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "PersonNode{" +
                "no=" + no +
                '}';
    }
}