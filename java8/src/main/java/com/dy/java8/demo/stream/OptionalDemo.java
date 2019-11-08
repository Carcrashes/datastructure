package com.dy.java8.demo.stream;

import com.dy.java8.demo.entity.Employee;
import com.dy.java8.demo.entity.GodNess;
import com.dy.java8.demo.entity.Man;
import com.dy.java8.demo.entity.NewMan;
import org.junit.Test;

import java.util.Optional;

/**
 * @author dingyu
 * @description Optional常用方法测试
 * Optional快速定位空指针位置
 * @date 2019/11/8
 */
public class OptionalDemo {

    @Test
    public  void test(){
        //创建一个Option容器实例
        Optional<Employee> employee = Optional.of(new Employee());
        //获取一个Employee实例
        Employee employee1= employee.get();
    }

    @Test
    public void test2(){
        //创建一个空实例
        Optional<Object> empty = Optional.empty();
        Optional<Employee> employee = Optional.ofNullable(new Employee());//如果Employee为空，则创建一个空实例，否则返回Optional<Employee>实例

        //判断是否含值 有值返回True.否则返回false
        if (employee.isPresent()){
            System.out.println(employee.get());
        }

        //orElse()
        employee.orElse(null);//如果没有值设置为null，null为设置的默认值

    }


    @Test
    public void test3(){
        //orElseGet(Supplier s)
        Optional<Employee> op = Optional.of(new Employee("dy",11,888, Employee.Status.FREE));
        Employee employee = op.orElseGet(() -> new Employee());  //是一个函数式接口。可以进行处理

        //map返回Optional<T> 直接返回子类类型
        Optional<String> s = op.map((e) -> e.getName());
        System.out.println(s.get());

        //flatMap 函数式接口，需要返回Optional<U> 对象
        Optional<String> s1 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(s1.get());
    }

    @Test
    public  void test4(){
        //传统写法
        GodNess god=new GodNess("La");
        Man man=new Man();
        String name=getGodNessName(man);//如果man为空就可能爆出空指针异常
        System.out.println(name);

        //java8 Optional容器方式
        Optional<NewMan> op=Optional.ofNullable(null); //进行封装，避免空指针异常
        String godeNessName2 = getGodeNessName2(op);
        System.out.println(godeNessName2);


        Optional<GodNess> go=Optional.ofNullable(null);
        Optional<NewMan> mo=Optional.ofNullable(new NewMan(go));
        String name2 = getGodeNessName2(mo);
        System.out.println(name2);


    }

    //避免出现空指针异常，设置默认值
    public String getGodeNessName2(Optional<NewMan> man){
        return man.orElse(new NewMan())
                        .getGodNess()
                        .orElse(new GodNess("aa"))
                        .getName();

    }

    public String getGodNessName(Man man){
        if (man==null || man.getGodNess()==null){
            return "自由女神";
        }
        return man.getGodNess().getName();
    }
}
