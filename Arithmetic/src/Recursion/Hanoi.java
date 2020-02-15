package Recursion;
//递归与分治算法解决汉诺塔问题
//关于汉诺塔的问题描述之前在博客里说过非常多了
//这里直接把算法代码给出来，代码比较少
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3,'A','B','C');
    }

    public static void hanoi(int n,char a,char b,char c){
        //最简单的情况是只有一个盘子
        if (n == 1){//直接拿到c
            System.out.println("把 " + n + " 从 " + a + " -> " + c);
        }else{
            hanoi(n - 1,a,c,b);
            System.out.println("把 " + n + " 从 " + a + " -> " + c);
            hanoi(n - 1,b,a,c);
        }
    }
}
