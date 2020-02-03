package Recursion;

import java.util.Arrays;
//http://www.7k7k.com/swf/49842.htm

public class Eight_Queen {//八皇后问题
    /*策略：
    * 1)第一个皇后先放第一行第一-列
    * 2)第二个皇后放在第二行第- -列、然后判断是否OK，如果不OK,继续放在第二列、第三列、依次把所有列都放
       完，找到一个合适
    * 3)继续第三个皇后，还是第- -列、第二列.... 直到第8个皇后也能放在- -个不冲突的位置，算是找到了一个正确解
    * 4)当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后， 放到第-列的所有正确解，
        全部得到.
    * 5)然后回头继续第一个 皇后放第二列，后面继续循环执行1,2,3,4的步骤
*/
    int MAX = 8;//八皇后
    static int count = 0;
    int[] array = new int[MAX];
    public static void main(String[] args) {
         /*
        * 理论上应该创建- -个二 维数组来表示棋盘，但是实际上可以通过算法，用- -个一维数组即 可解决问题. arr[8]=
        * {0,4,7,5,2,6, 1,3}//对应arr 下标表示第几行，即第几个皇后，arr[i]=val,val 表示第计1个皇后，放在第i+1
        * 行的第val+1列
*/
         Eight_Queen queen = new Eight_Queen();
         queen.check(0);
        System.out.printf("一共有%d种解法\n",count);

    }
    private void check(int n){
        if(n == MAX){
            print();
            return;
        }
        for (int i = 0; i < MAX; i++) {
            //从每行的第一列开始放置
            array[n] = i;
            //放完判断是否冲突
            if(judge(n)){//成立说明不冲突
                //接着放下放
                check(n + 1);
            }
            //冲突后会放到当前行的下一列
        }
    }
    private boolean judge(int n){//检测放置第n个皇后时是否与前面已经放置的是否有冲突
        for (int i = 0; i < n; i++) {
            //array[i]实际上表示第i个皇后放置在第array[i]列上
            //array[i] == array[n]就表示第i行第array[i]列上放置的皇后是否与第n行第array[n]列上的皇后是否有冲突
            //即判断两个皇后是否在同一列
            //Math.abs(n - i) == Math.abs(array[n] - array[i]判断是否在同一斜线上
            if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    private void print(){//如果出来结果就打印一个结果
        count++;
        System.out.println(Arrays.toString(array));
    }

}
