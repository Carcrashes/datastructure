package com.dy.datastructure.huffmancode;

import java.util.*;

/**
 * @author dingyu
 * @description 霍夫曼编码
 * @date 2019/12/13
 */
public class HuffmanCode {

    public static void main(String[] args) {
        //测试压缩
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length); //40
        byte[] huffmanCodesBytes= huffmancodeZip(contentBytes);
        System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodesBytes) + " 长度= " + huffmanCodesBytes.length);

        //测试解压
        byte[] decode = decode(huffmancodes, huffmanCodesBytes);
        System.out.println("解压结果-------------------------");
        System.out.println(Arrays.toString(decode));
    }

    /**
     * 编码表 生成霍夫曼树对应的霍夫曼编码
     * Map<Byte,String> 的形式 类似于{32=01,100=11000}
     */
    private static Map<Byte,String> huffmancodes=new HashMap<>();

    /**
     * 转换为编码
     */
    private static StringBuilder stringBuilder=new StringBuilder();



    /**
     * 转换为HuNode结点
     * @param bytes
     * @return
     */
    public static List<HuNode> getNodes(byte[] bytes){
        List<HuNode> nodes=new ArrayList<HuNode>();
        //遍历bytes 统计每个byte出现次数->map[key,value]
        Map<Byte,Integer> counts=new HashMap<>();
        for (byte b:bytes){
            Integer count=counts.get(b);
            if (count==null){
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }
        }
        //将每个键值转换为Node对象
        for (Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new HuNode(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    /**
     * 结点信息构建成为霍夫曼树
     * @param nodes
     * @return
     */
    public static HuNode createHuffmanTree(List<HuNode> nodes){
        while (nodes.size()>1){
            //排序
            Collections.sort(nodes);
            //取出最小两个结点
            HuNode left=nodes.get(0);
            HuNode right=nodes.get(1);

            //构建新二叉树
            HuNode parent=new HuNode(null,left.wight+right.wight);// 构建的新的二叉树数据域是没有值
            parent.setLeft(left);
            parent.setRight(right);

            //移除原数组最小两个元素，然后将新构建二叉树加入数组
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        //返回根节点
        return nodes.get(0);
    }

    /**
     * 传入的node结点的所有叶子结点的霍夫曼编码
     * @param node
     * @param code
     * @param stringBuilder
     */
    public static void getCodes(HuNode node,String code,StringBuilder stringBuilder){
        StringBuilder append=new StringBuilder(stringBuilder);
        //将code加入到append
        append.append(code);
        //如果node==null不处理
        if (node !=null){
            //判断当前节点是叶子结点和非叶子结点
            if (node.data==null){ //非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left,"0",append);
                //向右递归
                getCodes(node.right,"1",append);
            }else{
                //说明是一个叶子结点
                //就表示找到某个叶子结点的最后
                huffmancodes.put(node.data,append.toString());
            }

        }

    }

    /**
     * 方便调用进行重载
     * @param root
     * @return
     */
    private static Map<Byte,String> getCodes(HuNode root){
        if (root==null){
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",stringBuilder);
        //处理右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmancodes;
    }

    /**
     * 将字符串对应的byte数组转换为经过霍夫曼编码压缩之后的byte[]
     * @param bytes  原来字符串对应的byte[]
     * @param huffmancodes 生成霍夫曼编码表
     * @return 返回霍夫曼编码压缩之后的byte[]
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmancodes){
            //根据霍夫曼编码表，将源字符串转换为压缩过后的数据
            StringBuilder stringBuilder=new StringBuilder();
            for (byte b:bytes){
                stringBuilder.append(huffmancodes.get(b));
            }
            System.out.println("压缩霍夫曼编码："+stringBuilder+" 长度:"+stringBuilder.length());
            int len=0;
            if (stringBuilder.length() % 8==0){
                len=stringBuilder.length()/8;
            }else{
                len=stringBuilder.length()/8+1;
            }
            byte[] huffmancodeBytes=new byte[len];
            int index=0;//记录这是第几个byte
            for (int i=0;i<stringBuilder.length();i+=8){
                String str;
                if (i+8>stringBuilder.length()){
                    //不够8位
                    str=stringBuilder.substring(i);
                }else{
                    str=stringBuilder.substring(i,i+8);
                }
                //将str转换一个byte，放入到huffmancodeBytes
                huffmancodeBytes[index]=(byte)Integer.parseInt(str);
                index++;
            }
            return huffmancodeBytes;
    }

    /**
     * 压缩
     * @param bytes 要进行压缩的字符串
     * @return 经过压缩之后的byte数组
     */
    public static byte[] huffmancodeZip(byte[] bytes){
        //创建对应结点
        List<HuNode> nodes = getNodes(bytes);
        //创建霍夫曼树
        HuNode root = createHuffmanTree(nodes);
        //创建霍夫曼编码表
        Map<Byte, String> codes = getCodes(root);
        //进行压缩 得到霍夫曼编码
        byte[] huffmanCodeBytes = zip(bytes, codes);
        return huffmanCodeBytes;
    }


    /**----------------解压--------------------*/
    /**
     * 将byte转换为二进制
     * @param flag 标识是否需要补高位 true 需要补高位 false 标识不补
     * @param b 传入byte
     * @return 对应二进制的字符串  （返回的是补码）
     */
    private static String byteToString(boolean flag,byte b){
        //使用临时变量存储b,将b转换为int
        int tmp=b;
        if (flag){
            tmp |=256;//按位与 256->1 0000 0000 | 0000 0001 -->1 0000 0001
        }
        String str=Integer.toBinaryString(tmp);//返回的是tmp对应的补码
        if (flag){
            return str.substring(str.length()-8);
        }else {
            return str;
        }
    }

    /** 解压
     * @param huffmancodes
     * @param huffmancodeByte
     * @return
     */
    public static byte[] decode(Map<Byte,String> huffmancodes,byte[] huffmancodeByte){
        //先得到huffmanByte对应的二进制的字符串 形式入10101001.. 这个字符串长度一致，但是形式不一定
        StringBuilder stringBuilder=new StringBuilder();
        //将byte数组转换为字符串
        for (int i=0;i<huffmancodeByte.length;i++){
            byte b=huffmancodeByte[i];
            boolean flag=(i==huffmancodeByte.length-1);
            stringBuilder.append(byteToString(flag,b));
        }
        System.out.println("解压霍夫曼编字符串:"+stringBuilder.toString()+"解压霍夫曼编码长度"+stringBuilder.length());
        //将字符串按照指定霍夫曼编码进行解码
        Map<String,Byte> map=new HashMap<>();
        for (Map.Entry<Byte,String> entry:huffmancodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //创建集合存放byte
        List<Byte> list=new ArrayList<>();
        // i 可以理解为索引，扫描stringBuilder
        for (int i=0;i<stringBuilder.length();){
            int count=1;
            boolean flag=true;
            Byte b=null;
            while (flag){
                String key=stringBuilder.substring(i,i+count);//i 不动。让count移动 ，指定匹配到一个字符
                b=map.get(key);
                if (b==null){
                    //说明未找到
                    count++;
                }else{
                    flag=false;
                    //匹配到
                }
            }
            list.add(b);
            i+=count; //i直接移动到count
        }
        // 当for循环结束 list中就放了所有字符 将list字符存放到byte中
        byte[] b=new byte[list.size()];
        for (int i=0;i<b.length;i++){
            b[i]=list.get(i);
        }
        return b;
    }


}


/**
 * 结点
 */
class HuNode implements Comparable<HuNode>{

    Byte data;// 存放数据字符本身 比如a=97

    int wight;//权重 表示字符出现次数

    HuNode left;

    HuNode right;

    public HuNode(Byte data, int wight) {
        this.data = data;
        this.wight = wight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public HuNode getLeft() {
        return left;
    }

    public void setLeft(HuNode left) {
        this.left = left;
    }

    public HuNode getRight() {
        return right;
    }

    public void setRight(HuNode right) {
        this.right = right;
    }


    @Override
    public int compareTo(HuNode o) {
        //从小到大排序
        return this.wight-o.wight;
    }

    @Override
    public String toString() {
        return "HuNode{" +
                "data=" + data +
                ", wight=" + wight +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);

        if (this.left !=null){
            this.left.preOrder();
        }

        if (this.right !=null){
            this.right.preOrder();
        }
    }
}
