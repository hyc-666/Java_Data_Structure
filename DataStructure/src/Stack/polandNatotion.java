package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class polandNatotion {//逆波兰表达式

    public static void main(String[] args) {
        //定义逆波兰表达式的字符串
        //(3+4)*5-6
         String str = "3 4 + 5 * 6 -";
        List<String> list = getListString(str);
        System.out.println(calculate(list));


    }
    public static List<String> getListString(String s){
        String[] list = s.split(" ");//用空格分隔字符串
        List<String> ret = new ArrayList<String>();
        for (String ele : list){
            ret.add(ele);
        }
        return ret;
    }
    public static int calculate(List<String> s){
        Stack<String> stack = new Stack<String>();
        for(String item : s){
            if(item.matches("\\d+")){//使用正则表达式取出多位数
                stack.push(item);
            }else{
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                //匹配运算符
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }
                else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }

        }
        return Integer.parseInt(stack.pop());
    }
}
