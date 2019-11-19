package com.dy.nio.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author dingyu
 * @description NIO
 *  缓存区：在nio中，缓存区作用是存储数据，底层应用就是数组，数组就是存储不同数据类型得数据。
 *  根据数据类型不同，nio包提供对应类型的缓存区，除boolean以外，
 *  ByteBuffer
 *  CharBuffer
 *  ShortBuffer
 *  LongBuffer
 *  IntBuffer
 *  FolatBuffer
 *  DoubleBuffer
 *  上诉缓存区管理规则一致，通过allocate() 获取缓冲区
 *
 *  缓冲区存取数据的两个核心方法
 *  put():向缓存区中存储数据
 *  get():向缓冲区中获取数据
 *
 *  缓存区中的四个核心属性
 *  mark：标记，表示当前postition的位置，可以使用reset() 恢复到mark的位置
 *  position ：位置：表示缓存区正在操作的数据    position<=limit<=capacity
 *  limit; 界限：表示缓存区中可以操作数据的容量，（limit后的数据是不能操作（不能读写））
 *  capacity; 容量，标识缓存区中最大存储数据容量，一旦声明不可以修改（底层是数组，数组大小一旦声明不可以再修改）
 *
 *  直接缓存区和非直接缓存区：
 *      直接缓存区：通过allocateDirect() 方法分配，将缓存区建立在物理内存中，可以提高效率
 *      非直接缓存区：通过allocate() 方法分配，将缓存区建立在JVM的内存中
 *
 * @date 2019/11/12
 */
public class BufferDemo {

    @Test
    public void test(){
        //获取缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //输出四个核心属性
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //存入数据到缓存区中区
        String str="abcde";
        //写数据模式
        byteBuffer.put(str.getBytes());

        System.out.println(byteBuffer.position());  //操作位置移动到第五个位置，其余没有变
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //filp()切换到读数据模式
        byteBuffer.flip();
        System.out.println(byteBuffer.position());  //操作位置回到0位置，
        System.out.println(byteBuffer.limit()); // 有效数据只有5个，所以limit为5
        System.out.println(byteBuffer.capacity()); //容量不变为10

        byte[] bs=new byte[byteBuffer.limit()];
        byteBuffer.get(bs); //获取缓存中数据
        System.out.println(new String(bs,0,bs.length));
        //核心属性
        System.out.println(byteBuffer.position());  //读取操作位置第5位置，
        System.out.println(byteBuffer.limit()); // 有效数据只有5个，所以limit为5
        System.out.println(byteBuffer.capacity()); //容量不变为10

        //rewind(): 可重复读
        byteBuffer.rewind();

        //核心属性
        System.out.println(byteBuffer.position());  //读取操作位置第0位置，
        System.out.println(byteBuffer.limit()); // 有效数据只有5个，所以limit为5
        System.out.println(byteBuffer.capacity()); //容量不变为10

        //clear(); 清空缓存区 此时缓存区数据实际存在的，只是数据指针混乱，初始化原来位置
        byteBuffer.clear();
        System.out.println(byteBuffer.position());  //读取操作位置第0位置，
        System.out.println(byteBuffer.limit()); // 1024
        System.out.println(byteBuffer.capacity()); //1024

        System.out.println(byteBuffer.get()); //数据清空之后。实际存在数据，转换为ASCII编码。


    }

    @Test
    public  void test2(){
            String str="abcde";
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            byteBuffer.put(str.getBytes());

            System.out.println(byteBuffer.position());

            //切换为读的模式
            byteBuffer.flip();

            //获取缓冲流中数据
            byte[] des=new byte[byteBuffer.limit()];
            byteBuffer.get(des,0,2);
            System.out.println(new String(des,0,2));
            System.out.println(byteBuffer.position());

            //mark() 标记
            byteBuffer.mark();
            byteBuffer.get(des,2,2);
            System.out.println(new String(des,2,2));
            System.out.println(byteBuffer.position());

            //reset //恢复到mark标记的位置
            byteBuffer.reset();
            System.out.println(byteBuffer.position()); //恢复到mark标记位置

            //hasRemaining  判断缓冲区中还有没有可以操作的数据
            if(byteBuffer.hasRemaining()){
                int remaining = byteBuffer.remaining();//获取缓存区中可以操作的数据个数
                System.out.println(remaining);
            };
    }

    @Test
    public void test3(){
        //直接缓存区
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024); //分配直接缓存区
        System.out.println(byteBuffer.isDirect());//判断是否是直接缓存区
        //非直接缓存区
        ByteBuffer byteBuffer1=ByteBuffer.allocate(1024);//分配非直接缓存区
        System.out.println(byteBuffer1.isDirect());


    }
}
