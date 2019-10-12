package com.dy.datastructure.linkedlist;

import java.util.Stack;

/**
 * @author dingyu
 * @description 单链表（无顺序和有序添加）
 * @date 2019/10/11
 */
public class SingleLinkListDemo {

    public static void main(String[] args) {
            //无序编码
            SingleLinkList linkList=new SingleLinkList();
            HeroNode node=new HeroNode(1,"宋江","第一名");
            HeroNode node1=new HeroNode(2,"吴淞","第二名");
            HeroNode node2=new HeroNode(4,"铁塔","第三名");
            HeroNode node3=new HeroNode(3,"铁塔","第三名");
           /* linkList.addNode(node);
            linkList.addNode(node1);
            linkList.addNode(node2);
            linkList.showLinkedList();
            */

            //有序添加
          /*  linkList.addNodeOrder(node);
            linkList.addNodeOrder(node1);
            linkList.addNodeOrder(node2);
            linkList.addNodeOrder(node3);
            linkList.showLinkedList();
            */

          //修改节点
          HeroNode temp=new HeroNode(1,"小宋","第一名~~~");
          linkList.addNodeOrder(node);
          linkList.addNodeOrder(node1);
          linkList.addNodeOrder(node2);
          linkList.addNodeOrder(node3);
          //linkList.updateNode(temp);
          //linkList.showLinkedList();

          //System.out.println("删除节点后");
          //删除节点
          //linkList.delNode(1);
          //linkList.showLinkedList();

          //获取节点长度
          //System.out.println(linkList.length(linkList.getHead()));



        //测试倒数第k个节点
        //linkList.showLinkedList();
        //System.out.println( );
        //HeroNode lastNode= linkList.findLastNodeByIndex(linkList.getHead(), 2);
        //System.out.println(lastNode);
        //linkList.findLastNodeByIndex(linkList.getHead(),4);
        //linkList.findLastNodeByIndex(linkList.getHead(),-1);

        //节点反转
       /*
        System.out.println("反转之前单链表");
        linkList.showLinkedList();
        System.out.println("反转之后单链表");
        linkList.resverseNode(linkList.getHead());
        linkList.showLinkedList();
        */

       //逆序打印链表
        linkList.reverserPrint(linkList.getHead());

    }
}

/**
 * 定义单链表
 *  1.
 */
class SingleLinkList{

    private HeroNode head=new HeroNode(0,"",""); //头节点 不包含数据

    /**
     * 获取头节点
     * @return
     */
    public HeroNode getHead() {
        return head;
    }


    /**
     *  获取单链表节点长度
     * @param head 头节点
     * @return
     */
    public static int length(HeroNode head){
        if (head.next==null){
            return 0;
        }
        HeroNode temp=head.next;
        int length=0;
        while (true){
            if (temp==null){
                break;
            }
            length++;
            temp=temp.next;
        }
        return length;
    }

    /**
     * 新浪面试题 （查找单链表中倒数第k个节点）
     * @param head
     * @param index
     * @return
     */
    public HeroNode findLastNodeByIndex(HeroNode head,int index){
        if (head.next==null){
            System.out.println("链表为空");
        }
        //获取单链表长度
        int size=length(head);
        //倒数第k个节点，即使size-k个节点
        if (index < 0|| index>size){
            throw new IndexOutOfBoundsException("节点越界");
        }
        //进行遍历查找size-index节点
        HeroNode currentNode=head.next;
        for (int i=0;i<size-index;i++){
            currentNode=currentNode.next;
        }
        return currentNode;
    }

    /**
     * （腾讯面试题） 单链表反转
     * @param head
     */
    public  void resverseNode(HeroNode head){
        if (head.next==null || head.next.next==null){
            return;
        }
        //定义一个辅助变量 遍历单链表
        HeroNode curr=head.next;
        HeroNode next=null;//当前节点的下一个节点
        HeroNode reverseHead=new HeroNode(0,"","");//新头节点
        //遍历原来单链表节点，将其取出，每个移动到新头节点的最前端。
        while (curr !=null){
            next=curr.next;//临时保存节点
            curr.next=reverseHead.next;//将当前下一个节点指到新头节点的最前端
            reverseHead.next=curr;
            curr=next;//将curr节点后移
        }
        head.next=reverseHead.next;//将原头节点指向了新头节点的下个节点
        reverseHead.next=null;//将新头节点指向去除。让垃圾回收
    }

    /**
     * 逆序打印单链表
     * 方式一：向将单链表反转，然后遍历打印，这种方式会改变单链表结构，不建议使用
     * 方式二：利用栈的数据结构，将各个节点压入栈中，利用栈的先进后出特性，实现单链表的逆序打印
     * @param head
     */
    public void  reverserPrint(HeroNode head){
        //使用方式二的方式进行
        if (head.next==null){
            System.out.println("这是一个空链表");
        }
        //创建一个栈对象，存储链表
        Stack<HeroNode> stack=new Stack<>();
        HeroNode current=head.next;
        //遍历链表压入到栈中
        while (current.next !=null){
            stack.add(current);
            current=current.next;
        }
        //遍历栈 取出栈顶元素
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }


    /**
     * 添加节点 不考虑编号顺序
     * @author dingyu
     * @date 2019/10/11
     * @param headNode
     * @return void
     */
    public void addNode(HeroNode headNode){

        //1.找到当前链表的最后节点
        //2.将最后这个节点的next 指向新的节点
        //3.头节点不能动，需要一个辅助变量temp
        HeroNode temp=head;
        //查找最后一个节点
        while(true){
            if (temp.next ==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=headNode;
    }

    /**
     * 按照编码顺序添加节点
     * @author dingyu
     * @date 2019/10/11
     * @param heroNode
     * @return void
     */
    public  void addNodeOrder(HeroNode heroNode){
        //头节点位置不动，将头节点赋值给临时变量
        HeroNode temp=head;
        boolean flag=false;//标志位 判断该节点是否添加到链表中
        while (true){
            //判断链表是否为空
            if(temp.next==null){
                break;
            }
            if (temp.next.no>heroNode.no){
                break;
            }
            if (temp.next.no==heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;//节点后移
        }
        if (flag){
            System.out.println("节点编码已经存在。添加失败");
            return;
        }else{
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    /**
     * 显示链表元素
     */
    public void showLinkedList(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //不为空，则遍历链表
        HeroNode temp=head.next; //定义一个临时变量记录链表头节点，因为要保持链表头节点不变
        while (true){
            if (temp==null){
                break;
            }
            System.out.println(temp.toString());
            temp= temp.next;
        }

    }

    /**
     *  更新节点信息
     * @param newNode
     */
    public void updateNode(HeroNode newNode){
        if (head.next==null){
            System.out.println("链表为空");
        }
        HeroNode temp=head.next;
        boolean flag=false; //是否更新标志位
        while (true){
            if (temp==null){
                break;
            }
            if (temp.no==newNode.no){
                //找到需要替换的对象
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name=newNode.name;
            temp.nickName=newNode.nickName;
        }else{
            System.out.printf("不存在编号 %d 的节点需要更新 \n",newNode.no);
        }
    }


    /**
     * 删除节点
     * @param no
     */
    public void delNode(int no){
        if (head.next==null){
            System.out.println("链表为空");
        }
        HeroNode temp=head;
        boolean flag=false;//删除标志位
        while (true){
            if (temp.next==null){
                break;
            }
            if (temp.next.no == no){
                flag=true;
                break;
            }
            temp=temp.next; //链表后移
        }
        if (flag){
            temp.next=temp.next.next;
        }else{
            System.out.printf("不存在 %d 节点编号需要删除\n",no);
        }
    }


}

/**
 * 定义节点 包含编号 姓名  昵称 next域节点
 */
class HeroNode{

    public int no;//编号
    public String name;//姓名
    public String nickName;//昵称
    public HeroNode next;//next域节点


    public HeroNode(int no, String name, String nickName){
        this.no=no;
        this.name=name;
        this.nickName=nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}