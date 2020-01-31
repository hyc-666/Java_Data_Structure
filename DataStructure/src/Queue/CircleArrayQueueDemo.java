package Queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {//把数组模拟的队列改进为环形队列

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);//实际队列最大长度是3
        char key = ' ';//接受用户的输入
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("e(exit):退出程序");
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 'e':
                    loop = false;
                    break;
                case's':
                    try{
                        queue.showQueue();
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case'g':
                    try {
                        int val = queue.getQueue();
                        System.out.println(val);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        queue.head();
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }
        }
        scanner.close();
        System.out.println("程序退出。。。。");
    }
}
class CircleArrayQueue{
    private int maxSize;//表示队列最大长度
    private int front;//表示队列头
    private int rear; //表示队列尾。
    private int[] array;//这是队列里的数组

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = 0;
        rear = 0;
    }
    //判断满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }
    //判断空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加
    public void add(int data){
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        array[rear] = data;
        rear = (rear + 1) % maxSize;
    }
    //取数据，出队
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取数据");
        }
        //先保存下取出的数据
        int value = array[front];
        front = (front + 1) % maxSize;
        return value;
    }
    //展示队列
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }

        for (int i = front; i < front + size(); i++) {
            System.out.printf("array[%d] = %d\n",i % maxSize,array[i % maxSize]);
        }

    }
    //计算当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
    //查看当前队列头
    public void head(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        System.out.println("当前队列的头为：" + array[front]);
    }
}