package Tree;

public class ArrBinaryTree {//顺序存储二叉树

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        //谦虚遍历
        arrBinaryTree.preOrder();//1,2,4,5,3,6,7
    }
    /**
     * ➢顺序存储二叉树的特点:
     * 1) 顺序二叉树通常只考虑完全二叉树
     * 2) 第n个元素的左子节点为2*n+ 1
     * 3) 第n个元素的右子节点为2*n+2
     * 4) 第n个老素的父节点为(n-1)/ 2
     */
    private int[] arr;//村塾数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //二叉树前序遍历的方法

    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index){
        //需要注意空数组
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        //前序遍历，先输出当前结点
        System.out.println(arr[index]);
        //向左递归遍历
        if(index * 2 + 1 < arr.length){
            preOrder(index * 2 + 1);
        }
        //向右递归遍历
        if(index * 2 + 2 < arr.length){
            preOrder(index * 2 + 2);
        }

    }
    public void preOrder(){
        this.preOrder(0);
    }
}
