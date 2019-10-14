package com.dy.datastructure.linkedlist;

/**
 * @author dingyu
 * @description  双向链表Demo
 * @date 2019/10/14
 */
public class DoubleLinkedListDemo {
}

/**
 * 双向链表
 */
class DoubleLinkedList{

    private Node head=new Node(0,"");

    public Node getHead() {
        return head;
    }

    /**
     * 添加节点（直接添加末尾）
     * @author dingyu
     * @date 2019/10/14
     * @param node
     * @return void
     */
    public  void addNode(Node node) {
        //遍历找到最后节点
        Node temp = head;
        while (true) {
            if (node.next == null) {
                break;
            }
            temp = temp.next;
        }
        //将最后节点得下一个节点执行 添加节点
        temp.next = node;
        //将新添加节点得上一个节点指向 链表最后节点
        node.pre = temp;
    }

    /**
     *  添加节点（顺序添加）
     * @param node
     */
    public void addNodeOrder(Node node){
            Node temp=head;
            boolean flag=false;
            while (true){
                if (temp.next==null){
                    break;
                }
                if (temp.next.no==node.no){
                    flag=true;
                    break;
                }
                temp.next=temp;
            }
            if (flag){
                temp.next=temp.next.next;
                temp.next.pre=temp;
            }
    }


    /**
     * 根据编号删除节点，并返回删除节点信息
     * @param no
     * @return
     */
    public Node del(int no){
        if (head.next==null){
            System.out.println("链表为空，无删除数据");
            return null;
        }
        Node temp=head.next;//辅助节点
        boolean flag=false; //标志位
        //双向链表可以实现自我删除，不需要找到节点的上一个节点。
        while (true){
            if (temp==null){
                break;
            }
            if (temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.pre.next=temp.next.next;
            temp.next.pre=temp.pre;
        }
        //返回删除节点变量
        return temp;
    }

    /**
     * 更新节点信息
     * @param node
     * @return
     */
    public Node updNode(Node node){
        if (head.next== null){
            System.out.println("链表为空");
        }
        Node temp=head,next;
        boolean flag=false;
        while(true){
            if (temp.next==null){
                break;
            }
            if (temp.no==node.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name=node.name;
        }
        return temp;
    }

    /**
     * 展示列表所有元素信息
     */
    public  void  showLinkedList(){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        Node temp=head.next;
        while (true){
            if (temp.next ==null){
                break;
            }
            System.out.println(temp.toString());
            temp=temp.next;
        }
    }
}


/**
 *链表节点
 */
class Node{

    public  int no;//编码

    public String name;//名称

    public Node next;//指向下一个节点

    public Node pre;//指向前一个节点

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
}
