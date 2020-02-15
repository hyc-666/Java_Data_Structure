package Dynamic;

import java.util.Arrays;

//动态规划问题解决背包问题
public class KnapsackProblem {
    /**
     * 问题描述：
     * 有个背包容量为4磅，需要把下列商品装入背包
     * 吉他，1磅，价值1500
     * 音响，4磅，价值3000
     * 电脑，3磅，价值2000
     * 问应当怎样装能使得背包中的商品的总价值最大
     * 注意：一个商品只能装一次
     */
    public static void main(String[] args) {
        int[] w = {1,4,3};//保存物品的重量
        int[] val = {1500,3000,2000};//商品的价值
        int m = 4;//背包的容量
        int n = w.length;
        //实际上就是一个填表的过程
        /**
         * (1) v[i][0]=v[0]i]=0;/表示 填入表第- -行和第一列是0
         * (2)当w[i]>j时: v[i][j]=v[i-1][j] // 当准备加入新增的商品的容量大于当前背包的容量时，
         * 就直接使用上一个单元格的装入策略
         * (3)当j>=w[j]时: v[i]i]-max {v[i-1][j], v[ij+[i-1]j-wil}
         * 当准备加入的新增的商品的容量小于等于当前背包的容量,装入的方式:
         * v[i-1][j]:就是 上一个单元格的装入的最大值
         * v[j]:表示当前商品的价值
         * v[i-1][j-w[]] :装入i-1 商品，到剩余空间j-w[j]的最大值
         * 当j>=w[j时: v[i][j]=max{v[-1]ij], v[i]+v[-1]j-w[i]} :
         */
        //创建这个表
        //v[i][j]表示前i个物品能够装入容量为j的背包的商品的最大价值
        int[][] v = new int[n + 1][m + 1];
        //第一行和第一列都为0，和序号对应，同时也可以有意义
        for (int i = 0; i <v.length ; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v.length; i++) {
            v[0][i] = 0;
        }
        //为了体现放入过程再定一个数组记录放入情况
        int[][] res = new int[n + 1][m + 1];
        //这里注意循环是从1开始的，而上面的存储价值和重量要防止越界，需要对w[i]和val[i]减一
        for (int i = 1; i < v.length; i++) {  //不处理第一行
            for (int j = 1; j < v[0].length; j++) {  // 不处理第一列
                if(w[i - 1] > j){
                    v[i][j] = v[i - 1][j];
                }else {
//                    v[i][j] = Math.max(v[i - 1][j],val[i - 1] + v[i - 1][j - w[i - 1]]);
//                    res[i][j] = Math.max(v[i - 1][j],val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]){
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        res[i][j] = 1;
                    }else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出查看这个表
        for(int[] col : v){
            System.out.println(Arrays.toString(col));
        }
        System.out.println("================================");
        //给出最终分配过程
        int i = res.length - 1;
        int j = res[0].length - 1;
        while (i > 0 && j > 0){
            if(res[i][j] == 1){
                System.out.printf("第%d个商品放入背包\n",i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
