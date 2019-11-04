package com.dy.datastructure.stack;

import java.util.Stack;

/**
 * @author dingyu
 * @description 中缀表达式计算
 * @date 2019/10/22
 */
public class Calculator {


    public static void main(String[] args) {
        int cal = cal("10+2*3+2");
        System.out.printf("计算结果为:"+cal);
        System.out.println();

    }

    /**
     * 1.通过index值当索引，来遍历表达式
     * 2.定义一个数栈和一个符号栈，发现数栈就直接入数栈
     * 3.扫描到符号
     *  3.1 如果符号栈为空，就直接入栈
     *  3.2 如果符号栈不为空，就比较当前操作符号于栈顶元素，如果优先级大于栈顶元素优先级，则直接入栈
     *  3.3 如果当前优先级小于栈顶元素优先级，则从数栈中取出栈顶元素和次栈元素，然后从符号栈取出元素，进行计算，然后将结果入栈。
     * 4.表达式扫描完毕，就顺序的从数栈和符号栈中pop相应的元素，并运行
     * 5.最后数栈中的值，即为表达式值
     */

    public static int cal(String expression){
        //数栈
        Stack<Integer> numStack=new Stack<>();
        Stack<Integer> operStack=new Stack<>();
        int index=0;//索引扫描表达式
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch='0';//将每次扫描得到char保存到ch中
        String keepNum="";//用于拼接，多位数
        while(true){
            //扫描表达式
            ch=expression.substring(index,index+1).charAt(0);
            if (isOper(ch)){
                if (!operStack.isEmpty()){
                    //优先级比较
                    if (priority(ch)>priority((char)operStack.peek().intValue())){
                        operStack.push(Integer.valueOf(ch));
                    }else{
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res= clas(num1, num2, oper);
                        numStack.push(res);
                        //将当前操作符号也入栈
                        operStack.push(Integer.valueOf( ch));
                    }
                }else {
                    operStack.push(Integer.valueOf(ch));
                }
            }else{
                //数栈直接入栈
                //numStack.push(Integer.valueOf(ch) );
                //处理多位数情况
                keepNum+=ch;
                //判断是否最后一位，就直接入栈
                if (index ==expression.length()-1){
                    numStack.push(Integer.valueOf(keepNum));
                }else{
                    //判断下一位是否为数字，如果是数字则继续扫描，是运算符号则进行入栈
                    if(isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.valueOf(keepNum));
                        keepNum="";//此时keepNum需要清空
                    }
                }
            }
            index++;
            if (index>=expression.length()){
                //结束扫描
                break;
            }
        }
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=clas(num1,num2,oper);
            numStack.push(res);
        }
        return numStack.pop();
    }


    /**
     * 判断是否是符号
     * @param ch
     * @return
     */
    public static boolean isOper(char ch){
        return ch=='*'|| ch=='+'||ch=='/'||ch=='-';
    }

    /**
     * 判断优先级
     * @param ch
     * @return
     */
    public static int priority(char ch){
        if (ch=='*'|| ch=='/'){
            return 1;
        }else if(ch=='+'||ch=='-'){
            return 0;
        }else{
            return -1; //假定目前的表达式
        }
    }


    public static int clas(int num1, int num2, int oper){
        if (oper=='*'){
            return num1*num2;
        }else if (oper=='/'){
            return num2 / num1;
        }else if (oper=='+'){
            return num1+num2;
        }else {
            return num2-num1;
        }
    }


}
