package Queue.ArrayQueen;

import java.util.Scanner;

public class ArrayQueueDemo {//数组模拟队列实现

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';//接受用户的输入
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("e(exit):退出程序");
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):获得队列头的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 'e':
                    loop = false;
                    break;
                case's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
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
                default:
                    break;
            }
        }
        System.out.println("程序退出。。。。");
    }
}
class ArrayQueue{
    private int maxSize;//表示队列最大长度
    private int front;//表示队列头,指向队列头的前一个数据
    private int rear; //表示队列尾。指向队列尾的前一个数据
    private int[] array;//这是队列里的数组

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = -1;//初始时头和尾全部指向一个空位置
        rear = -1;
    }
    //判断满
    public boolean isFull(){
        return rear == maxSize - 1;
    }
    //判断空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据，入队
    public void addQueue(int data){
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        rear++;
        array[rear] = data;
    }
    //获取队列数据，出队
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        //move();
        return array[front];//每次把队列头部的元素取出
    }
    //显示队列
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空");
        }
        for (int i = front + 1; i < rear + 1; i++) {
            System.out.printf("  %d  ",array[i]);
        }
        System.out.println();
    }
    //但是这个队列目前还有一个问题就是不断出队和不断的入队很容易就会导致头指针和尾指针都往后移
    //会导致数组前半部分是空的，而且数组很容易满
    //为此需要改进出队的操作
    //出队前先把所有的数据都要往前移
    //为此需要把队列头固定尾数组开头
//    private void move(){
//        for (int i = 0; i < rear + 1; i++) {
//            array[i] = array[i + 1];
//        }
//    }
}