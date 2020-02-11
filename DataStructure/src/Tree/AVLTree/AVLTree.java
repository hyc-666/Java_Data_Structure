package Tree.AVLTree;

public class AVLTree {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[ ]arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.print("树的高度");
        System.out.println(avlTree.getRoot().height());
        System.out.println("左子树高度" + avlTree.getRoot().leftHeight());
        System.out.println("右子树高度" + avlTree.getRoot().rightHeight());
        System.out.println("根节点" + avlTree.getRoot());
        System.out.println("根节点的左子节点:" + avlTree.getRoot().left);
//        System.out.println("根节点的左子节点的左子节点:" + avlTree.getRoot().left.left);
        System.out.println("根节点的左子节点:" + avlTree.getRoot().right);
//        System.out.println("根节点的右子节点的右子节点:" + avlTree.getRoot().right.right);
    }
    private Node root;
    public void add(Node node){
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
    }

    public Node getRoot() {
        return root;
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

    //返回左子树的盖度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }
    //返回右子树的高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }
    //计算以当前结点为根节点的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height()) + 1;
    }

    //左旋调整平衡二叉树
    public void leftRotate(){
        //保存当前根节点的值
        Node newNode = new Node(value);
        //新节点的左子树设为当前结点的左子树
        newNode.left = left;
        //把新节点的右子树设置为当前结点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值换成右子树的值
        value = right.value;
        //把当前结点的右子树设置为当前结点的右子树的右子树
        right = right.right;
        //把当前结点的左子树设置为新的结点
        left = newNode;
    }
    //右旋调整平衡二叉树
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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
        //当添加完结点后发现右子树的高度大于左子树的高度
         if(rightHeight() - leftHeight() > 1){
             //如果当前结点的右子树的左子树高度大于当前结点的右子树的右子树的高度
             if(right != null && right.leftHeight() > right.rightHeight()){
                 //需要对当前结点的右子树进行右旋转
                 right.rightRotate();
                 leftRotate();
             }else {
                 leftRotate();//左旋转
             }
             return;
         }
         //左子树的结点大于右子树的结点
         if(leftHeight() - rightHeight() > 1){
             //如果当前结点的左子树的右子树的高度大于当前结点左子树的左子树的高度
             if(left != null && left.rightHeight() > left.leftHeight()) {
                 //当前结点的左子树进行左旋转
                 left.leftRotate();
                 rightRotate();
             }else{//否则直接右旋转
                 rightRotate();
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
