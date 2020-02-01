package SingleLinkedNode;

import java.net.HttpRetryException;
import java.net.PortUnreachableException;

public class SingleLinkedNode {
    public static void main(String[] args) {
        LinkedNode list = new LinkedNode();
        list.addOrder(new HeroNode(1,"宋江","及时雨"));
        list.addOrder(new HeroNode(4,"林冲","豹子头"));
        list.addOrder(new HeroNode(3,"吴用","智多星"));
        list.addOrder(new HeroNode(2,"卢俊义","玉麒麟"));
        System.out.println("修改前");
        list.show();
        list.updata(1,"songjiang","jishiyu");
        list.updata(2,"yuqilin");
        list.updataNickName(3,"zhiduoxing");
        list.delete(3);
        list.show();


        list.show();
    }
}
class LinkedNode{
    //创建一个带头节点的单链表来管理水浒英雄
    HeroNode head = new HeroNode(0,"","");//头节点不保存任何信息
    //获得头节点

    public HeroNode getHead() {
        return head;
    }

    //计算长度
    public int length(HeroNode head){
        HeroNode heroNode = head;
        int size = 0;
        while(heroNode != null){
            size++;
            heroNode = heroNode.next;
        }
        return size;
    }
    public void add(HeroNode heroNode){//添加结点，默认是尾部插入
        //遍历，找到最后的结点
        HeroNode node = head;
        while(node.next != null){
            node = node.next;
        }
        node.next = heroNode;
    }
    //按编号顺序插入
    public void addOrder(HeroNode heroNode){
        //找前一个结点
        HeroNode prev = head;

        while(prev.next != null){

            HeroNode node = prev.next;
            if(node.no < heroNode.no){
                prev = prev.next;
            }
            else if(node.no > heroNode.no){
                heroNode.next = node;
                prev.next = heroNode;
                return;
            }
            else{
                System.out.println("编号已经存在，不能添加");
                break;
            }
        }
        if(prev.next == null){
            prev.next = heroNode;
        }
    }
    public void updata(int no,String name,String nickname){//更新数据
        HeroNode node = head.next;
        if(node == null){
            System.out.println("当前没有数据");
            return;
        }
        while (node != null){
            if(node.no == no){
                node.name = name;
                node.nickName = nickname;
                return;
            }
            node = node.next;
        }
        System.out.println("没有找到当前编号");
    }
    public void updata(int no,String nickname){//方法的重载，跟新昵称
        HeroNode node = head.next;
        if(node == null){
            System.out.println("当前没有数据");
            return;
        }
        while (node != null){
            if(node.no == no){
                node.nickName = nickname;
                return;
            }
            node = node.next;
        }
        System.out.println("没有找到当前编号");
    }
    public void updataName(int no,String name){
        HeroNode node = head.next;
        if(node == null){
            System.out.println("当前没有数据");
            return;
        }
        while (node != null){
            if(node.no == no){
                node.name = name;
                return;
            }
            node = node.next;
        }
        System.out.println("没有找到当前编号");
    }
    public void updataNickName(int no,String nickName){
        HeroNode node = head.next;
        if(node == null){
            System.out.println("当前没有数据");
            return;
        }
        while (node != null){
            if(node.no == no){
                node.nickName = nickName;
                return;
            }
            node = node.next;
        }
        System.out.println("没有找到当前编号");
    }
    public void show(){
        HeroNode node = head.next;
        while(node != null){
            System.out.println(node.toString());
            node = node.next;
        }
    }
    //根据编号移除
    public void delete(int no){
        HeroNode prev = head;
        if(prev.next == null){
            System.out.println("当前列表为空");
            return;
        }
        while (prev.next != null){
            HeroNode node = prev.next;
            if(node.no == no){
               prev.next = node.next;
               return;
            }
            prev = prev.next;
        }
        System.out.println("没有找到当前编号");
    }

}
class HeroNode{
    int no;
    String name;
    String nickName;
    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode" + this.no + "{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
