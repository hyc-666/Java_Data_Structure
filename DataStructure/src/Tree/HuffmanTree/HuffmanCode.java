package Tree.HuffmanTree;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    //哈夫曼编码
    public static void main(String[] args) {

        //测试文件的压缩
//        String srcFile = "D:\\Git\\Java_Data_Structure\\DataStructure\\src\\Tree\\HuffmanTree\\src.bmp";
//        String dstFile = "D:\\Git\\Java_Data_Structure\\DataStructure\\src\\Tree\\HuffmanTree\\dst.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩文件ok~~~");
        //测试解压
        String zipFile = "D:\\Git\\Java_Data_Structure\\DataStructure\\src\\Tree\\HuffmanTree\\dst.zip";
        String dstFile = "D:\\Git\\Java_Data_Structure\\DataStructure\\src\\Tree\\HuffmanTree\\src2.bmp";
        unZipFile(zipFile,dstFile);
        System.out.println("解压完成");


        /**String str = "i like like like java do you like a java";
        //但是结果不总是静如人意的，字符串一发生变化结果就变了
        //于是就可以直接调用
        byte[] bytes = huffmanZip(str);

        System.out.println(Arrays.toString(bytes));

        //测试哈夫曼编码的解码
        String sourceStr = decode(huffmanCodes,bytes);
        System.out.println(sourceStr);
         */
        /**
        byte[] bytes = str.getBytes();
        List<HuffmanNode> nodes = getNodes(bytes);
        System.out.println(nodes);

        System.out.println("返回哈夫曼树的根节点");
        HuffmanNode root = creatHuffmanTree(nodes);
        System.out.println("前序遍历哈夫曼树：");
        root.preOrder();

        System.out.println("生成的哈夫曼编码表:");
        getCodes(root);
        System.out.println(huffmanCodes);

        byte huffmanByte[] = zip(bytes,huffmanCodes);
        System.out.println("哈夫曼编码后的字节数组:");
        System.out.println(Arrays.toString(huffmanByte));
         */
    }

    /**
     *  编写对文件解压的方法
     * @param zipFile 需要解压的文件
     * @param dstFile 解压以后的文件路径
     */
    public static void unZipFile(String zipFile,String dstFile){
        //创建I/O流
        InputStream  is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try{
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffman_bytes = (byte[])ois.readObject();

            Map<Byte,String> codes = (Map<Byte, String>) ois.readObject();
           byte[] decodes =  decode(codes,huffman_bytes);
            os = new FileOutputStream(dstFile);
            os.write(decodes);
        }catch (Exception e){

        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     *  编写一个方法，用于将文件压缩
     * @param srcFile 源文件路径
     * @param dstFile 压缩后的文件存放路径
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建I/O流
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try{
            is = new FileInputStream(srcFile);
            //创建一个和源文件一样大小的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffman_bytes = huffmanZip(new String(b));
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffman_bytes);
            oos.writeObject(huffmanCodes);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                os.close();
                oos.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    //将字符串对应的byte[]数组，通过生成的哈夫曼编码表，返回一个压缩后的哈夫曼编码串byte[]

    /**
     *
     * @param bytes 原始数组
     * @param huffmanCodes 生成的哈夫曼编码表
     * @return 返回一个byte[] bytes,是一个只有0和1的字符串，把这个字符串的0和1,8位一组生成byte[]
     * 即转成一个字节数组，每一位是一个byte类型的数据，是补码存储的，如果最高位是1则是个负数，
     * 会转成反码再转成原码，最后转成byte类型的数据
     */
    private static byte[] zip(byte bytes[],Map<Byte,String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println(stringBuilder.toString());
        //创建压缩有存储编码的byte[]数组
        int index = 0;//记录是第几个byte
        int len = (stringBuilder.length() + 7) / 8;
        byte[] res = new byte[len];
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if(i + 8 > stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            res[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }
        return res;
    }
    //哈夫曼编码的解码

    /**
     *
     * @param huffmanCodes 已经统计转换好的哈夫曼编码表
     * @param huffmanBytes 需要解码的哈夫曼字节数组
     * @return 暂时返回一个字节数组，后续会转为字符串
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //转成二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,huffmanBytes[i]));
        }
        //System.out.println("转成的二进制序列是：" + stringBuilder.toString());
        //按照编码表对二进制字符串进行解码
        //将编码表反过来
        Map<String,Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        //System.out.println(map);
        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        //扫描二进制序列，匹配每个字符
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = stringBuilder.substring(i,i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //list中存放原来的每一个字符对象,需要转成字符数组，再转成字符串
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        //System.out.println(Arrays.toString(b));
        //拿到字符数组以后，转成一个字符串返回
        return b;
    }

    /**
     *  将一个字节数据转为二进制的序列字符串
     * @param flag 标志是否需要补高位
     * @param b 字节数据参数
     * @return 返回一个只有0和1的二进制字符串
     */
    private static String byteToBitString(boolean flag,byte b){
        //byte里面没有转成二进制序列的方法，需要把b转成int
        int temp = b;
        //如果是正数需要补高位，不然正数时只会返回低位有效数字
        if(flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);//返回的是对应的补码
        if(flag){
            return str.substring(str.length() - 8);
        }
        return str;
    }

    //将上述主方法里的过程封装成一个方法

    /**
     *
     * @param str 直接传进一个字符串
     * @return 返回压缩后的字节数组
     */
    private static byte[] huffmanZip(String str){
        //变成字节数组
        byte[] bytes = str.getBytes();
        //分割成一个个字符串列表
        List<HuffmanNode> nodes = getNodes(bytes);
        //创建哈夫曼树，返回哈夫曼树的根节点
        HuffmanNode root = creatHuffmanTree(nodes);
        //生成哈夫曼编码表
        Map<Byte,String> huffmantable = getCodes(root);
        //根据哈夫曼编码表压缩成字节数组
        byte[] huffmanBytes = zip(bytes,huffmantable);
        return huffmanBytes;
    }
    //为了调用方便，重载getCodes
    private static Map<Byte,String> getCodes(HuffmanNode root){
        if (root == null) {
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",stringBuilder);
        //处理右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    //生成哈夫曼树对应的哈夫曼编码表
    //存储在Map<Byte,String>里
    static Map<Byte,String> huffmanCodes = new HashMap<Byte ,String>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将传入的结点的所有哈夫曼编码拿到，放入huffmanCodes集合
     * @param node 传入的结点，默认从根节点开始
     * @param code 路径，左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接字符串
     */
    private static void getCodes(HuffmanNode node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前是否是叶子节点
            if(node.data == null){//说明是非叶子节点
                //递归找叶子节点
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //享有递归
                getCodes(node.right,"1",stringBuilder2);
            }else { //说明是叶子节点，直接加入
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }
    //前序遍历
    private void preOredr(HuffmanNode root){
        if (root == null) {
            throw new IllegalArgumentException("参数错误，是一颗空树");
        }
        root.preOrder();
    }
    private static List<HuffmanNode> getNodes(byte[] bytes){
        ArrayList<HuffmanNode> nodes = new ArrayList<HuffmanNode>();
        //遍历bytes,统计每个字符出现的次数
        Map<Byte,Integer> counts = new HashMap<>();
        for(byte b : bytes){
            Integer count = counts.get(b);
            if(count == null){//没有出现就统计一下
                counts.put(b,1);
            }
            else {
                counts.put(b,count + 1);
            }
        }
        //遍历map把每一个键值对变成一个HuffmanNode
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){
            nodes.add(new HuffmanNode(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
    private static HuffmanNode creatHuffmanTree(List<HuffmanNode> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);//先排序
            //取出两个最小权值的二叉树
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            HuffmanNode parent = new HuffmanNode(null,leftNode.weight + rightNode.weight);
            //创建新的二叉树没有data，只有权值
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
class HuffmanNode implements Comparable<HuffmanNode>{
    Byte data;//字符本身
    int weight;//权值（字符出现的次数)
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight - o.weight;
    }
}