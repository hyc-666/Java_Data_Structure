package Stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        String key ="";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:显示栈的情况");
            System.out.println("exit:退出");
            System.out.println("posh:压栈");
            System.out.println("pop:出栈");
            System.out.print("请选择：");
            key = scanner.next();
            switch (key){
                case "push":
                    System.out.println("请输入一个将要压栈的数：");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "show":
                    arrayStack.list();
                    break;
                case "pop":
                    try{
                        int ret = arrayStack.pop();
                        System.out.println("出栈的数据是" + ret);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
                default:
                    break;
            }

        }
        System.out.println("退出。。。");
    }
}

class ArrayStack{//数组模拟栈结构
    private int maxSize;//栈的大小
    private int[] stack;//栈中的数组
    private  int top = -1;//栈顶
    //栈底是固定的，可以不用标记

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    public boolean isFull(){//判断栈满
        return top == maxSize - 1;
    }
    public boolean isEmpty(){
        return top == -1;
    }
    public void push(int value){
        if(isFull()) {
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top] = value;
    }
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //查看栈目前的情况
    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        System.out.println("栈顶");
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
        System.out.println("栈底");
    }
}