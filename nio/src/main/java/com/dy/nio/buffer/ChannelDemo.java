package com.dy.nio.buffer;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import static java.nio.file.StandardOpenOption.READ;

/**
 * @author dingyu
 * @description 通道
 *  Channel：
 *      用于源节点和目标节点的连接，在JAVA nio中负责缓冲区的数据传输，Channel本身是不存储数据的，因此需要配合缓冲区进行传输，
 *  主要实现类：
 *      FileChannel:用于读取、写入、映射和操作文件的通道
 *      SocketChannel:通过 TCP 读写网络中的数据。
 *      ServerSocketChannel:可以监听新进来的 TCP 连接，对每一个新进来的连接都会创建一个 SocketChannel。
 *      DataGramChannel:通过 UDP 读写网络中的数据通道。
 *
 *   获取通道：Java 针对通道的类都提供了getChannel() 方法
 *     本地IO；
 *          FileInputStream/FileOutputStream
 *          RandAccessFile
 *       网络：
 *          Soceket
 *          ServerSocket
 *          DateGramSocket
 *
 *    jdk 1.7 中 nio.2 中针对各个通道提供了静态方法open()
 *    jdk 1.7 中nio.2 中的Files类的newByteChannel();获取通道
 *
 *    通道之间数据传输：
 *    transferto()   数据传输到目标通道
 *    transferFrom()  从源通道获取数据
 *
 *    分散（scatter） 聚焦（Gather）
 *      分散读取：将通道中的数据分散到多个缓冲区
 *      聚集写入：将多个缓冲区中的数据聚集到newByteChannel中
 *
 *     字符集 Charset
 *      编码：字符串转换为字节数组过程
 *      解码：字节数组转换为字符串过程
 * @date 2019/11/12
 */
public class ChannelDemo {

    //通道完成文件的复制（非直接缓冲区）
    @Test
    public void test(){
        FileInputStream inputStream=null;
        FileOutputStream outputStream=null;

        FileChannel in=null;
        FileChannel out=null;
        try {
            inputStream=new FileInputStream("bw.txt");
            outputStream=new FileOutputStream("bw1.txt");

            //1.获取通道
            in=inputStream.getChannel();
            out=outputStream.getChannel();

            //2.分配指定大小缓冲区
            ByteBuffer bytes=ByteBuffer.allocate(1024);

            //3.数据写入缓存区  in.read(bytes)
            while (in.read(bytes) !=-1){
                //4.缓冲区的数据写入通道
                bytes.flip();//切换成读模式
                out.write(bytes);
                bytes.clear();
            }

            //关闭通道和流
            in.close();
            out.close();
            inputStream.close();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out !=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    //通道完成文件复制(直接缓冲区) 内存映射
    @Test
    public void test2(){
        Instant now = Instant.now();
        //jdk 1.7 open方式获取通道
        //open(path,option) paths.get("1.jpg","2.jpg") 获取多个文件资源， StandardOpenOption 操作模式
        try {
            //获取通道 StandardOpenOption.READ 读
            FileChannel inChannel=FileChannel.open(Paths.get("bw.txt"), READ);
            //StandardOpenOption.WRITE(写模式)
            // StandardOpenOption.CREATE（文件不存在就创建，存在就报错）
            // StandardOpenOption.CREATE_NEW(不管文件存在不存在都新建文件)
            FileChannel outChanel = FileChannel.open(Paths.get("bw2.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, READ);

            //内存映射文件 将文件直接映射到物理内存中  只有ByteBuffer支持这种方式
            // MapMode.READ_ONLY 只读模式
            MappedByteBuffer inMap = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMap = outChanel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //直接对缓冲区进行读写操作
            byte[] dst=new byte[inMap.limit()];
            //读取缓冲区
            inMap.get(dst);
            //写入缓冲区
            outMap.put(dst);

            //关闭
            inChannel.close();
            outChanel.close();
            Instant end = Instant.now();
            System.out.println(Duration.between(now,end).toMillis());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //通道之间的数据传输（直接缓存区的方式）
    @Test
    public void test3() throws IOException {
        //获取通道
        FileChannel inChannel = FileChannel.open(Paths.get("bw.txt"), READ);
        FileChannel outChannel=FileChannel.open(Paths.get("bw3.txt"), READ,StandardOpenOption.CREATE,StandardOpenOption.WRITE);

        //通道之间数据传输 (文件复制两种方式)
        inChannel.transferTo(0,inChannel.size(),outChannel);
        //outChannel.transferFrom(inChannel,0,inChannel.size());

        //关闭通道
        inChannel.close();
        outChannel.close();

    }

    //分散和聚集
    @Test
    public void test4() throws IOException {
        //"rw" 读写模式
        RandomAccessFile randomFile=new RandomAccessFile("bw.txt","rw");

        //获取通道
        FileChannel channel = randomFile.getChannel();

        //分配缓冲区 指定大小
        ByteBuffer buffer1=ByteBuffer.allocate(100);
        ByteBuffer buffer2=ByteBuffer.allocate(1024);

        //分散读取
        ByteBuffer[] buffers={buffer1,buffer2};
        channel.read(buffers);

        for (ByteBuffer buffer:buffers){
            buffer.flip();
        }

        System.out.println(new String(buffers[0].array(),0,buffers[0].limit()));
        System.out.println("--------------------------------------------------------");
        System.out.println(new String(buffers[1].array(),0,buffers[1].limit()));


        //聚集写入
        RandomAccessFile raf2= new RandomAccessFile("2.txt","rw");
        FileChannel fileChannel=raf2.getChannel();

        //写入
        fileChannel.write(buffers);

    }

    //字符集 解码和编码
    @Test
    public void test5() throws CharacterCodingException {
        //字符集
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = stringCharsetSortedMap.entrySet();
        entries.stream().forEach(e->System.out.println(e.getKey()+"---"+e.getValue()));


        //解码和编码
        //定义编码
        Charset cst=Charset.forName("GBK");
        //获取解码器
        CharsetEncoder charsetEncoder = cst.newEncoder();
        //获取编码器
        CharsetDecoder charsetDecoder = cst.newDecoder();

        //定义缓存区
        CharBuffer charBuffer=CharBuffer.allocate(1024);
        charBuffer.put("aaaaaaaaaaa");

        //进行解码  解码为二进制
        //切换为读模式
        charBuffer.flip();

        ByteBuffer encode = charsetEncoder.encode(charBuffer);
        for (int i=0;i<11;i++){
            System.out.println(encode.get());
        }

        //进行编码
        encode.flip();
        CharBuffer decode = charsetDecoder.decode(encode);
        System.out.println(decode.toString());

        System.out.println("------------------------------");
        Charset charset = Charset.forName("UTF-8");
        encode.flip();
        CharsetDecoder charsetDecoder1 = charset.newDecoder();
        CharBuffer decode1 = charsetDecoder1.decode(encode);
        System.out.println(decode1.toString());



    }
}
