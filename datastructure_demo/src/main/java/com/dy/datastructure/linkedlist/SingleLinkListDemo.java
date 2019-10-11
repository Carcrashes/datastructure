package com.dy.datastructure.linkedlist;

/**
 * @author dingyu
 * @description 单链表（无顺序）
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
            linkList.addNodeOrder(node);
            linkList.addNodeOrder(node1);
            linkList.addNodeOrder(node2);
            linkList.addNodeOrder(node3);
            linkList.showLinkedList();

    }
}

/**
 * 定义单链表
 *  1.
 */
class SingleLinkList{

    private HeroNode head=new HeroNode(0,"",""); //头节点 不包含数据

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