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

    public void addPerson(int nums){
        if (nums<1){
            System.out.println("nums值不能小于1");
        }
        PersonNode currPerson=null;
        for (int i=0;i<nums;i++){
            PersonNode person=new PersonNode(i);
            if (i==1){
                first=person;//初始链表第一个元素
                person.setNext(person);
                currPerson=first; //声明一个辅助变量
            }else{
                //将当前对象
                currPerson.setNext(person);
                person.setNext(first);
            }

        }

    }

    /**
     * 展示环形队列所有元素
     */
    public void showLinkedList(){
        if (first==null){
            System.out.println("This is empty linkedList");
            return;
        }
        //因为first不能动，所以需要一个临时变量
        PersonNode temp=first;
        while(true){
            System.out.printf("boy's number is %d",temp.getNo());
            if (temp.getNext()==first){
                break;
            }
        }
    }

    public  void countPerson(int startNo,int countNum,int nums){
        if(first==null|| startNo<1 || startNo>nums){
            System.out.println("参数有错，请确认后输入");
            return;
        }


        //创建一个辅助变量，指向最后一个节点/
        PersonNode helper=first;
        while (true){
            if (helper.getNext()==first){
                //找到最后一个节点
                break;
            }
            helper=helper.getNext();//节点后移
        }

        //报数前移动k-1位(点数时候包含本身)
        for (int j=0;j<startNo-1;j++){
            first=first.getNext();
            helper=helper.getNext();
        }

        //小孩报数时候，让first和helper同时移动指针m-1次
        while(true){
            if (helper==first){
                break;//圈中只有一个节点
            }
            for(int j=0;j<countNum-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //打印控制台输出
            System.out.printf("person was out order by no:",first.getNo());
            //将找到节点删除
            first=first.getNext();
            helper.setNext(first);
        }

        System.out.printf("last person number is,",first.getNo());
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