package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Transform {//中缀表达式转为后缀表达式

    public static void main(String[] args) {
        String s = "1+((2+3)*4)-5";
        List<String> list = toList(s);
        System.out.println("中缀表达式：" + list);
        List<String> res = toSuffixList(list);//将上一步的中缀表达式转为后缀表达式
        System.out.println("后缀表达式：" + res);
        System.out.println("表达式" + s +"的结果是" + PolandNatotion.calculate(res));

    }
    public static List<String> toList(String s){//分割中缀表达式
        List<String> ls = new ArrayList<String>();
        int i = 0;//用于遍历中缀表达式
        String str;
        char ch;
        do{
            if((ch = s.charAt(i)) > '9' || (ch = s.charAt(i)) <'0'){
                ls.add("" + ch);
                i++;
            }
            else{
                str = "";
                while (i < s.length() && (ch = s.charAt(i)) >= '0' && (ch = s.charAt(i)) <= '9'){
                    str += ch;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }
    public static List<String> toSuffixList(List<String> ls){//转为后缀表达式
        //定义符号栈
        Stack<String> s1 = new Stack<String>();
        //数栈由于没有出栈等的操作，所以直接使用ArrayList<String>
        List<String> s2 = new ArrayList<String>();
        for(String item : ls){
            //如果是数字，直接压入堆栈s2
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将（弹出s1栈,消除小括号

            }else {
                //比较优先级
                while (s1.size() != 0 && operation(s1.peek()) >= operation(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
            //将s1中剩余的运算符加入到s2中

        }
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }
    //写一个方法返回优先级
    public static int operation(String oper){
        switch(oper){
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                System.out.println("操作符错误");
        }
        return 0;
    }
}
