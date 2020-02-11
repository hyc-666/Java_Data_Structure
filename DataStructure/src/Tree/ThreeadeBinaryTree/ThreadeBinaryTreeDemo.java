package Tree.ThreeadeBinaryTree;

public class ThreadeBinaryTreeDemo {
    public static void main(String[] args) {
        //创建节点
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(8);
        TreeNode n5 = new TreeNode(10);
        TreeNode n6 = new TreeNode(14);
        ThreadeBinaryTree tree = new ThreadeBinaryTree(root);
        //目前只能手动把结点挂上0.0
        root.setLeft(n2);
        root.setRight(n3);
        n2.setLeft(n4);
        n2.setRight(n5);
        n3.setLeft(n6);
        //中序遍历
        tree.midOrder();
        //测试线索化
        tree.threadNode();
        System.out.println("n5的前驱节点" + n5.getLeft());
        System.out.println("n5的后继节点" + n5.getRight());
        System.out.println("使用线索化的方式遍历二叉树");

        /**tree.threadmidOrder();//这个线索化的遍历有问题，会死循环*/

    }
}
