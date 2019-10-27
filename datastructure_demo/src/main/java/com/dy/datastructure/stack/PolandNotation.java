package com.dy.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 栈模拟逆波兰表达式  (后缀表达式)
 *
 */
public class PolandNotation {


    public static void main(String[] args) {
            //约定后缀表达式之间,符号数字空格分离
        List<String> list = PolandNotation.getList("3 4 + 5 * 6 -");
        int calcuator = calcuator(list);
        System.out.println("计算结果为:"+calcuator);

    }

    /**
     * 将表达式转成集合
     * @param suffixExpreession
     * @return
     */
    public static List<String> getList(String suffixExpreession){
        List<String> list=new ArrayList<>();
        String[] split = suffixExpreession.split(" ");
        for (String str:split){
            list.add(str);
        }
        return list;
    }


    public static int calcuator(List<String> expression){
        if (expression ==null || expression.size()<1){
            return 0;
        }
        Stack<String> stack=new Stack<>();
        for (String str:expression){
            if (str.matches("\\d+")){
                //匹配是否是数,如果是数直接入栈
                stack.push(str);

            }else{
                //碰到是符号,这pop栈顶和次栈元素进行计算之后再入栈
                int num1=Integer.valueOf(stack.pop());
                int num2=Integer.valueOf(stack.pop());
                int res=0;
                if ("+".equals(str)){
                    res=num1+num2;
                }else if ("*".equals(str)){
                    res=num1 * num2;
                }else if ("/".equals(str)){
                    res=num2/num1;
                }else if("-".equals(str)){
                    res=num2-num1;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(String.valueOf(res));
            }

        }
        //将最后结果pop出去
        return Integer.valueOf(stack.pop());
    }



}
