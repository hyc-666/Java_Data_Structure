package Tree;

//线索二叉树

/**
 * 线索二叉树，
 * 指把一些空出来的指针按照一定顺序（二叉树的遍历顺序）用来存储结点的前驱节点或者后继节点
 */
public class ThreadeBinaryTree {
    TreeNode root;
    TreeNode prev = null;
    //线索化二叉树

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public ThreadeBinaryTree(TreeNode root) {
        this.root = root;
    }
    //重载线索化方法
    public void threadNode(){
        this.threadNode(root);
    }

    /**
     *
     * @param node 是当前需要线索化的二叉树
     */
    //以中序遍历顺序线索化二叉树
    public void threadNode(TreeNode node){
        if(node == null){
            System.out.println("不能线索化");
            return;
        }
        //线索化左子树
        threadNode(node.getLeft());
        //线索化当前结点
        if(node.getLeft() == null){
            node.setLeft(prev);
            //将左边类型置为前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        if(prev != null && prev.getRight() == null){
            prev.setRight(root);
            prev.setRightType(1);
        }
        prev = node;//每处理一个节点后，让当前结点是下一个结点的前驱节点
        //线索化右子树
        threadNode((node.getRight()));
    }

    /**
     * 由于二叉树被线索化之后，不能按照原来的方式去遍历这个二叉树
     * 因此当二叉树被线索化之后，遍历的方法也要做出相应的改变
     * 而且线索化以后的遍历顺序也应当和之前的遍历方式一样
     * 本节使用中序的方式线索化二叉树的，
     * 所以需要再写一遍线索化以后的二叉树的中序遍历
     */
    //线索化二叉树的遍历
    //很遗憾，这个线索化的二叉树的遍历是有问题的

    public void threadmidOrder(){
        //从root开始遍历
        TreeNode node = root;
        while (node != null){
            //寻找第一个前驱节点为1的结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前结点
            System.out.println(node);
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }
    //后序遍历
    public void lastOrder(){
        if(this.root != null){
            this.root.lastOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }
    public void midOrder(){
        if(this.root != null){
            this.root.midOrder();
        }else{
            System.out.println("二叉树为空");
        }
    }
}
class TreeNode {
    private int data;
    private TreeNode left;
    private TreeNode right;
    //记录指向的是左子树还是前驱节点
    //0表示指向的是左子树。1表示指向的是前驱节点
    private int leftType;
    //同样0表示指向的是右子树，1表示指向的是后继节点
    private int rightType;

    public TreeNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }
    //前序遍历
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
    //中序遍历
    public void midOrder(){
        //先递归向左子树中序遍历
        if(this.left != null){
            this.left.midOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.midOrder();
        }
    }
    //后序遍历
    public void lastOrder(){
        if(this.left != null){
            this.left.lastOrder();
        }
        if(this.right != null){
            this.right.lastOrder();
        }
        System.out.println(this);
    }
}