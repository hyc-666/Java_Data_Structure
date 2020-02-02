package SingleLinkedNode;

public class Josephu {//约瑟夫问题，使用单向循环链表完成
    public static void main(String[] args) {
        CircleLinkedList list = new CircleLinkedList();
        list.init(15);
        //list.show();
        list.start(8,6,15);//2,4,1,5,3
    }
}
//创建环形的链表
class CircleLinkedList{
    Child first = null;
    public void init(int n){//一共有多少个结点
        if(n < 1){
            throw new IllegalArgumentException("参数不正确");
        }
        Child cur = first;
        for (int i = 1; i <= n; i++) {
            Child child = new Child(i);
            if(i == 1){
                first = child;
                first.setNext(first);
                cur = first;
            }else{
                cur.setNext(child);
                child.setNext(first);
                cur = child;
            }
        }
    }
    //显示当前的状态
    public void show(){
        if(first == null){
            System.out.println("当前约瑟夫环为空");
            return;
        }
        Child cur = first;
        while(true){
            System.out.println("结点编号：" + cur.getNo());
            cur = cur.getNext();
            if(cur == first){
                break;
            }
        }
    }
    //startNo表示开始时的编号，countNum表示数几下，就是m的值，num表示最初有多少个结点
    public void start(int startNo,int countNum,int nums) {
        if (first == null || startNo < 0 || startNo > nums) {
            System.out.println("参数有误");
            return;
        }
        //让cur指向最后
        Child cur = first;
        for (int i = 1; i < nums; i++) {
            cur = cur.getNext();
        }
        //找到开始数的那个结点,让first指向它
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            cur = cur.getNext();
        }
        //first当前指向开始数的那个结点
        while (true) {
            //开始数
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                cur = cur.getNext();
            }
            //first就是要出圈的结点
            System.out.println("出圈的结点：" + first.getNo());
            //把first结点删除
            first = first.getNext();
            cur.setNext(first);
            if(cur == first){
                System.out.println("最后的结点是" + first.getNo());
                break;
            }
        }
    }
}
//先创建结点类
class Child{
    private int no;//编号
    private Child next;

    public Child(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Child getNext() {
        return next;
    }

    public void setNext(Child next) {//需要使用修改器来构成环
        this.next = next;
    }
}
