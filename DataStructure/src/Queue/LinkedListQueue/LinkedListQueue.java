package Queue.LinkedListQueue;

/**
 * @author hyc
 * @date 2020/5/19
 */
public class LinkedListQueue {
    ListNode head = null;//相当于队首
    //队尾经过计算得出
    //入队方法
    public void add(int val){
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
            return;
        }
        //入队插在队尾
        ListNode cur = head;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = node;
    }
    //出队操作，相当于删除头节点
    public Integer poll(){
        if (head == null){
            return null;
        }
        int res = head.val;
        head = head.next;
        return res;
    }
    //查看队列状态
    public void show(){
        for (ListNode node = head;node != null;node = node.next){
            System.out.print(node.val + "->");
        }
    }
    //查看队首元素
    public Integer front(){
        if (head == null){
            return null;
        }
        return head.val;
    }
    //查看队尾元素
    public Integer tail(){
        if (head == null){
            return null;
        }
      ListNode node = head;
      while (node.next != null){
          node = node.next;
      }
      return node.val;
    }
    //队列长度
    public int size(){
        int count = 0;
        ListNode node = head;
        while (node != null){
            count++;
            node = node.next;
        }
        return count;
    }
}
//内部结点
class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}