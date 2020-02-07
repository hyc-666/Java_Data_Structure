package Tree;

//定义二叉树
public class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    /**
     * 使用前序，中序,后序的方式来查询指定的结点
     * 前序查找思路
     * 1.先判断当前结点的no是否等于要查找的
     * 2.如果是相等，则返回当前结点
     * 3.如果不等，则判断当前结点的左子节点是否为空,如果不为空，则递归前序查找
     * 4.如果左递归前序查找，找到结点，则返回，否继续判断，
     * 当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找.
     * 中序查找思路
     * 1.判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
     * 2.如果找到，则返回，如果没有找到，就和当前结点比较，
     * 如果是则返回当前结点，否则继续进行右递归的中序查找
     * 3.如果右递归中序查找，找到就返回，否则返回null
     * 后序查找思路
     * 1.判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
     * 2.如果找到，就返回，如果没有找到，就判断当前结点的右子节点是否为空，
     * 如果不为空，则右递归进行后序查找,如果找到，就返回.
     * 3.就和当前结点进行，比如，如果是则返回，否则返回null
     */
    //前序遍历查找
    public HeroNode preSearch(int no){
        if(root != null){
            return root.preSearch(no);
        }
        return null;
    }
    //中序遍历查找
    public HeroNode midSearch(int no){
        if(root != null){
            return root.midSearch(no);
        }
        return null;
    }
    //后序遍历查找
    public HeroNode lastSearch(int no){
        if(root != null){
            return root.lastSearch(no);
        }
        return null;
    }
    //删除结点
    public void delete(int no){
        if(root != null){
            if(root.getNo() == no){
                root = null;
                return;
            }else{
                root.delete(no);
            }
        }else {
            System.out.println("空树不能删除");
        }
    }
}
