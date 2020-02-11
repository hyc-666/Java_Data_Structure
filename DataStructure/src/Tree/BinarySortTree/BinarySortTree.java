package Tree.BinarySortTree;

//二叉排序树
public class BinarySortTree {

    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        //中序遍历二叉树
//        System.out.println("中序遍历二叉树");
//        tree.infixOrder();
        //删除叶子节点2
//        System.out.println("删除2后");
//        System.out.println("删除有两个子树的结点7后");
//        tree.delNode(7);
//       tree.infixOrder();
//        //删除9后
//        System.out.println("删除9后");
//        tree.delNode(9);
//        tree.infixOrder();
//        System.out.println("删除1后");
//        tree.delNode(1);
//        tree.infixOrder();
        tree.delNode(12);
        tree.delNode(5);
        tree.delNode(3);
        tree.delNode(9);
        tree.delNode(2);
        tree.delNode(5);
        tree.delNode(7);
        tree.delNode(10);
        tree.delNode(1);
        tree.infixOrder();
    }

    private Node root;
    public void add(Node node){
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
    }
    public void infixOrder(){
        if (root == null) {
            System.out.println("二叉树为空");
        }else{
            root.infixOrder();
        }
    }
    //查找结点
    public Node search(int value){
        if (root == null) {
            return null;
        }
        return root.search(value);
    }
    //查找父节点
    public Node searchParent(int value){
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }
    //排序二叉树结点的删除
    /** 有三种情况
     * 1.删除叶子结点
     *   找到要删除的结点的父节点，然后让其子节点置空
     * 2.删除只有一个子树的结点
     *  找到要删除的结点父节点，让其指向要删除的结点的子节点，一共4种情况
     * 3.删除有两个子树的结点
     *  找到要删除的结点的父节点，然后比较要删除的结点与其兄弟结点的权值，
     *  再决定需要把哪一个子树往上提
     */
    /**
     * 找到以node结点为根节点的左侧值最小的结点，并删除然后返回
     * @param node 以这个结点作为根节点
     * @return 返回这个最小值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //查找最小值
        while (target.left!= null){
            target = target.left;
        }
        //删除这个最小结点
        delNode(target.value);
        return target.value;
    }
    //根据键值删除结点
    public void delNode(int value){
        if (root == null) {
            return;
        }
        //查找到要删除的结点
        Node targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        if(root.left == null && root.right == null){//如果需要删除的结点是根节点且当前二叉树只有一个结点
            root = null;
            return;
        }
        //找父节点
        Node parent = searchParent(value);
        if(targetNode.left == null && targetNode.right == null){ //如果要删除的是叶子结点
            //判断要删除的结点是父节点的左子节点还是父节点的右子节点
            if(parent.left!= null && parent.left.value == value){//说明是左子节点
                parent.left = null;
            }else if(parent.right != null && parent.right.value == value){//右子节点
                parent.right = null;
            }
        }else if(targetNode.left != null && targetNode.right != null){//删有两个子树的结点
            int minVal = delRightTreeMin(targetNode.right);//删除此节点右子树的左边的最小值
            //然后把这个右侧最小值替换到这个结点上
            targetNode.value = minVal;
        }else {//删除只有一个子树的结点
            //需要判断要删除的结点是否是根节点
            //判断是父节点的左子树还是父节点的右子树
            if (targetNode.left!= null){//如果要删除的结点有左子树
                if(parent != null) {
                    if (parent.left.value == value) {//如果要删除的结点是父节点的左子树
                        parent.left = targetNode.left;
                    } else {//是父节点的右子树
                        parent.right = targetNode.left;
                    }
                }else {
                    root = targetNode.left;
                }
            }else {//如果有右子树
                if(parent != null) {
                    //仍然需要判断是父节点的左子树还是右子树
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                }else{
                    root = targetNode.right;
                }
            }
        }

    }
}
//树的结点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
    //添加节点
    public void add(Node node){
        if (node == null) {
            return;
        }
        if (node.value < this.value){//往左边添加
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
    }

    /**
     * 根据关键值查找谋和结点
     * @param value 键值
     * @return 返回符合要求的结点
     */
    public Node search(int value){
       //不用判空
        if(this.value == value){
            return this;
        }else if(value < this.value){
            if (this.left == null){
                return null;
            }else{
              return this.left.search(value);
            }
        }else{
            if(this.right == null){
                return null;
            }else{
                return this.right.search(value);
            }
        }
    }

    /**
     * 查找某个结点的父节点
     * @param value 根据键值查找父节点
     * @return 返回该节点的父节点
     */
    public Node searchParent(int value){
        if((this.left != null && this.left.value == value) ||
        this.right != null && this.right.value == value){
            return this;
        }else{
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else{
                if(value >= this.value&& this.right != null){
                    return  this.right.searchParent(value);
                }else{
                    return null;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value = " + value +
                '}';
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
}
