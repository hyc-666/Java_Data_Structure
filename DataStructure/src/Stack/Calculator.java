package Stack;


public class Calculator {//使用栈来计算表达式的值
    public static void main(String[] args) {

        String str = "13+6*2-2";
        CalculateStack numsStack = new CalculateStack(10);//数栈
        OperStack operStack = new OperStack(10);//符号栈
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        char oper = 0;
        int res = 0;
        char ch = ' ';
        while (index < str.length()){
            ch = str.substring(index,index +1).charAt(0);
            if(operStack.isOper(ch)){
                //如果取出的是操作符
                //判断该当前符号栈是否为空
                if(!operStack.isEmpty()){
                    //比较优先级
                    if(operStack.priority(ch) <= operStack.priority(operStack.getTop())){
                        num1 = numsStack.pop();
                        num2 = numsStack.pop();
                        oper = operStack.pop();
                        res = numsStack.calculate(num1,num2,oper);
                        numsStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else{
                    operStack.push(ch);
                }
                index++;
            }else {
                //处理多位数的情况
                int num = 0;
                while (index < str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9'){
                    num = num * 10 + (str.charAt(index) - 48);
                    index++;
                }

                numsStack.push(num);
            }
            //判断是否扫描到表达式最后
        }
        while (true){
            //判断符号栈是否为空，计算最后结果
            if(operStack.isEmpty()){
                break;
            }
            num1 = numsStack.pop();
            num2 = numsStack.pop();
            oper = operStack.pop();
            res = numsStack.calculate(num1,num2,oper);
            numsStack.push(res);
        }
        System.out.println("表达式" + str + "的结果为："+ numsStack.pop());
    }
}
class CalculateStack {
    private int maxSize;
    private int[] stack;
    private  int top = -1;

    public CalculateStack(int maxSize) {
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
    //定义优先级
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }
        return -1;
    }
    //判断是不是一个操作符
    public boolean isOper(int oper){
        if(oper == '+' || oper == '-' || oper == '*' || oper == '/'){
            return true;
        }
        return false;
    }
    //计算方法
    public int calculate(int num1,int num2,char oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                try {
                    res = num2 / num1;
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            default:
                break;
        }
        return res;
    }
    public int getTop(){//查看栈顶
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return stack[top];
    }

}
class OperStack {//符号栈
    private int maxSize;
    private char[] stack;
    private  int top = -1;

    public OperStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new char[maxSize];
    }
    public boolean isFull(){//判断栈满
        return top == maxSize - 1;
    }
    public boolean isEmpty(){
        return top == -1;
    }
    public void push(char value){
        if(isFull()) {
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top] = value;
    }
    public char pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        char value = stack[top];
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
    //定义优先级
    public int priority(char oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }
        return -1;
    }
    //判断是不是一个操作符
    public boolean isOper(char oper){
        if(oper == '+' || oper == '-' || oper == '*' || oper == '/'){
            return true;
        }
        return false;
    }
    public char getTop(){//查看栈顶
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return stack[top];
    }
}