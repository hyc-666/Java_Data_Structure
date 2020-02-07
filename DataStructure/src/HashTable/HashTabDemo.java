package HashTable;

import java.util.Scanner;

/**
 * 散列表(Hash table，也叫哈希表)，
 * 是根据关键码值(Key value)而直接进
 * 行访问的数据结构。也就是说，它通
 * 过把关键码值映射到表中一个位置来
 * 访问记录，以加快查找的速度。这个
 * 映射函数叫做散列函数，存放记录的
 * 数组叫做散列表
 */
public class HashTabDemo {//使用数组+链表来实现哈希表

    public static void main(String[] args) {
        //创建哈希表
        HashTable hashTable = new HashTable(7);
        //还是写一个菜单
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("a(add)：添加");
            System.out.println("l(list)：列出");
            System.out.println("d(delete)：删除");
            System.out.println("s(serach)：查找");
            System.out.println("e(exit)：退出");
            key = scanner.next();
            char select = key.charAt(0);
            switch (select){
                case 'a':
                    System.out.println("请输入id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字：");
                    String name = scanner.next();
                    Employee employee = new Employee(id,name);
                    try {
                        hashTable.add(employee);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case'e':
                    loop = false;
                    break;
                case 'l':
                    hashTable.list();
                    break;
                case 'd':
                    System.out.println("请输入要删除的id号：");
                    id = scanner.nextInt();
                    hashTable.delete(id);
                    break;
                case 's':
                    System.out.println("输入id");
                    int ret = scanner.nextInt();
                    hashTable.find(ret);
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }
}
//使用哈希表来管路雇员信息
//雇员类
class Employee {
    int id;
    String name;
    Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
class LinkedEmployee{
    Employee head = null;
    private boolean search(int id){
        if (head == null) {
            return false;
        }
        Employee node = head;
        while (node != null){
            if(node.id == id){
                return true;
            }
            node = node.next;
        }
        return false;
    }
    //寻找某一个结点的前一个结点
    private Employee serachPrev(Employee node){
        //添加之前已经判空过，这里可以不用判空
        Employee prev = head;
        while (prev.next != null){
            if(prev.next.id == node.id){
                return prev;
            }
            prev = prev.next;
        }
        return prev;
    }
    //默认添加链表的最后
    public void add(Employee Emp){//现在来改进按照id的顺序添加
        if (head == null) {
            head = Emp;
            return;
        }
        //需要处理相同的id不能重复添加的情况
        if(search(Emp.id)){
            throw new IllegalArgumentException("此条记录已经存在");
        }
        //寻找前一个结点
        if(Emp.id < head.id){//则说明是头插
            Emp.next = head;
            head = Emp;
            return;
        }
        Employee node = head;
        while (node != null){
            if(node.id < Emp.id){
                node = node.next;
            }else {
                //找第一个比当前要添加的结点的id号大
                break;
            }
        }
        if (node == null) {//说明找到最后
            node = Emp;
            return;
        }
        //没有找到最后则找要插入结点之前的这个结点的前一个结点
        Employee prev = serachPrev(node);
        prev.next = Emp;
        Emp.next = node;


    }
    //列出链表信息
    public String list(){
        if (head == null) {
            return "链表为空";
        }
        Employee node = head;
        String str = "->";
        while (node != null){
            str = str + "->" + node.toString();
            node = node.next;
        }
        return str;
    }
    public void delete(int id){
        if (head == null) {
            System.out.println("不存在此条记录");
            return;
        }
        //因为单链表不能自我删除
        //需要留意删除的是否是头节点
        if (head.id == id){
            head = head.next;
            return;
        }
        //为了删除，需要记录前一个结点
        Employee prev = head;
        Employee node = head.next;
        while (node != null){
            if(node .id == id){
                break;
            }
            prev = prev.next;
            node = node.next;
        }
        if (node == null) {
            System.out.println("不存在此条记录");
        }
        else {
            prev.next = prev.next.next;
        }
    }
    public Employee find(int id){
        if (head == null) {
            return null;
        }
        Employee node = head;
        while (node != null){
            if(node.id == id){
                return node;
            }
            node = node.next;
        }
        return node;
    }

}
class HashTable {
    LinkedEmployee[] empListArray;
    int size;//表示一共有多少条链表，即数组长度

    public HashTable(int size){
        this.size = size;
        empListArray = new LinkedEmployee[size];
        //需要初始化每条链表，这很重要
        for (int i = 0; i < size; i++) {
            empListArray[i] = new LinkedEmployee();
        }
    }
    public void add(Employee employee){
        //根据id添加到哪条链表
        int index = hashFun(employee.id);
        empListArray[index].add(employee);
    }
    //使用简单取模来写出散列函数
    private int hashFun(int id){
        return id % size;
    }
    public void list(){
        for (int i = 0; i < size; i++) {
            System.out.printf("第%d条链表",i+ 1);
            System.out.println(empListArray[i].list());
        }
    }
    public void find(int id){
        int index = hashFun(id);
        Employee node = empListArray[index].find(id);
        if (node == null) {
            System.out.println("没有找到");
        }
        else {
            System.out.printf("在第%d条链表找到:",index + 1);
            System.out.print(node.toString());
            System.out.println();
        }
    }
    //根据id删除
    public void delete(int id){
        int index = hashFun(id);
        empListArray[index].delete(id);
    }
}
