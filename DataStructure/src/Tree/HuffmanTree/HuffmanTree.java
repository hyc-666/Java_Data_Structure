package Tree.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 构成赫夫曼树的步骤:
 * 1) 从小到大进行排序，将每一 个数据，每个数据都是一 一个节点，
 *    每个节点可以看成是-颗最简单的二叉树
 * 2) 取出根节 点权值最小的两颗二叉树
 * 3) 组成一颗新的二叉树,该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4) 再将这颗新的二叉树，以根节点的权值大小再次排序，不断重复1-2-3-4 的步骤，
 *    直到数列中，所有的数据都被处理，就得到-颗赫夫曼树
 */
public class HuffmanTree {//哈夫曼树

    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        root.preOrder();
    }

    /**
     *
     * @param root 传入一颗哈夫曼树的根节点即可前序遍历
     */
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            throw new IllegalArgumentException("参数错误，这是一颗空树");
        }
    }

    //创建哈夫曼树

    /**
     *
     * @param arr 需要创建成哈夫曼树的数组
     * @return 返回创建成功的哈夫曼树的根节点
     */
    public static Node createHuffmanTree(int[] arr){
        //把数组中的每个元素取出放在一个Node的List中
        List<Node> nodes = new ArrayList<Node>();
        for(int value : arr){
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            //System.out.println("未处理前第一次排序后: " + nodes);
            //取出权值最小的两个结点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            //构建新的二叉树
            parent.left = leftNode;
            parent.right = rightNode;
            //删除掉处理过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //把新创建的二叉树加到list中
            nodes.add(parent);
            //然后再排序重复此操作
            //System.out.println("第一次处理后：" + nodes);
        }
        return nodes.get(0);
    }
}
//创建二叉树的节点类
class Node implements Comparable<Node>{
    int value;//结点的权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
    //增加前序遍历
    public void preOrder(){
        //先输出父节点
        System.out.println(this);
        //向左子树递归遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //向右子树递归遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    //为了支持排序
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
