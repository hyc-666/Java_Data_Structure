package Tree.BinaryTree;

//使用前面的HeroNode作为一个结点
//使用二叉树管理之前的水浒英雄
public class BinaryTreeDemo {//二叉树

    public static void main(String[] args) {
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();

        //创建二叉树的根节点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode n2 = new HeroNode(2,"吴用");
        HeroNode n3 = new HeroNode(3,"卢俊义");
        HeroNode n4 = new HeroNode(4,"林冲");
        HeroNode n5 = new HeroNode(5,"关胜");
        //先手动创建二叉树，后续会递归创建二叉树
        binaryTree.setRoot(root);
        root.setLeft(n2);
        root.setRight(n3);
        n3.setRight(n4);
        n3.setLeft(n5);
        System.out.println("测试前序遍历");
        binaryTree.preOrder();
//        System.out.println("测试中序遍历");
//        binaryTree.midOrder();
//        System.out.println("测试后序遍历");
//        binaryTree.lastOrder();
//        System.out.println("测试前序遍历查找");
//        System.out.println(binaryTree.preSearch(15));
//        System.out.println("测试中序遍历查找");
//        System.out.println(binaryTree.midSearch(15));
//        System.out.println("测试后序遍历查找");
//        System.out.println(binaryTree.lastSearch(15));
        System.out.println("测试删除的功能");
        binaryTree.delete(5);
        binaryTree.preOrder();
    }
}
//创建二叉树的结点
class HeroNode {
    private int no;//编号
    private String name;//名字
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "no=" + no +
                ", name='" + name + '\'' +
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
    //根据id编号先序查找
    public HeroNode preSearch(int no){
        if(this.no == no){
            return this;
        }
        //左子树判空，然后向左子树递归查找
        HeroNode ret = null;//这是结果，事先置为空
        if(this.left != null){
            ret = this.left.preSearch(no);
        }
        //判断在左子树是否找到
        if(ret != null){
            return ret;
        }
        //右子树判空，然后向右递归前序查找
        if(this.right != null){
            ret = this.right.preSearch(no);
        }
        return ret;
    }
    //中序遍历查找
    public HeroNode midSearch(int no){
        //左子树判空，然后向左子树递归查找
        HeroNode ret = null;//这是结果，事先置为空
        if(this.left != null){
            ret = this.left.midSearch(no);
        }
        //判断在左子树是否找到
        if(ret != null){
            return ret;
        }
        //比较当前结点
        if(this.no == no){
            return this;
        }
        //右子树判空，然后向右递归前序查找
        if(this.right != null){
            ret = this.right.midSearch(no);
        }
        return ret;
    }
    //后序遍历查找
    public HeroNode lastSearch(int no){
        //左子树判空，然后向左子树递归查找
        HeroNode ret = null;//这是结果，事先置为空
        if(this.left != null){
            ret = this.left.lastSearch(no);
        }
        //判断在左子树是否找到
        if(ret != null){
            return ret;
        }
        //右子树判空，然后向右递归前序查找
        if(this.right != null){
            ret = this.right.lastSearch(no);
        }
        if(ret != null){
            return ret;
        }
        //比较当前结点
        if(this.no == no){
            return this;
        }
        return ret;
    }
    /**
     * 完成删除结点的操作
     * 规定:
     * 1)如果删除的节点是叶子节点，则删除该节点
     * 2)如果删除的节点是非叶子节点，则删除该子树
     * 思路
     * 首先处理：考虑如果树是空树root,如果只有一个root结点，则等价将二叉树置空
     * 1.因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删
     * 除结点，而不能去判断当前这个结点是不是需要删除结点
     * 2.如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将
     * this.left=nll;并且就返回(结束递归删除)
     * 3.如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将
     * this.right=null ;并且就返回(结束递归删除)
     * 4.如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
     * 5.如果第4步也没有删除结点,则应当向右子树进行递归删除.
     * 」
     */
    public void delete(int no){
        if(this.left != null){
            if(this.left.no == no){
                this.left = null;
                return;
            }
        }
        if(this.right != null){
            if(this.right.no == no){
                this.right = null;
                return;
            }
        }
        //想做递归
        if(this.left != null){
            this.left.delete(no);
        }
        if(this.right != null){
            this.right.delete(no);
        }
    }
}