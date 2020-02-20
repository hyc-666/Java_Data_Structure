package Floyd;

import java.util.Arrays;

//弗洛伊德算法计算最短路径
//弗洛伊德算法会把所有的点与点之间的最短路径计算出来，最后存在一张表里

/**
 * 1)和Dijkstra算法一样，弗洛伊德(Floyd)算法也是一种用于寻找给定的加权图中顶点间最短路径的算法。该算法
 * 名称以创始人之一、1978 年图灵奖获得者、斯坦福大学计算机科学系教授罗伯特.弗洛伊德命名
 * 2) 弗洛伊德算法(Floyd)计算图中各个顶点之间的最短路径
 * 3)迪杰斯特拉算法用于计算图中某一个顶点到其他顶点的最短路径。
 * 4)弗洛伊德算法VS迪杰斯特拉算法:迪杰斯特拉算法通过选定的被访问顶点，求出从出发访问顶点到其他顶点
 * 的最短路径;弗洛伊德算法中每-一个顶点都是出发访问点，所以需要将每一一个顶点看做被访问顶点，求出从每
 * -个顶点到其他顶点的最短路径。
 */
public class Floyd {
    /**
     *1)胜利乡有7个村庄(A, B,C,D,E,F, G)
     * 2)各 个村庄的距离用边线表示(权)，比如A-B距离5公里
     * 3)问:如何计算出各村庄到其它各村庄的最短距离?
     */
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{ 0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{ 5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{ 7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{ N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{ N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{ N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{ 2, 3, N, N, 4, 6, 0};
        Graph graph = new Graph(vertex.length,matrix,vertex);
        graph.floyd();
        graph.show();
    }
}
//图
class Graph{
    private char[] vertex;//顶点集
    private int[][] dis; //保存各个顶点到其他顶点的最短距离，结果也保存在这里，一开始的功能相当于邻接矩阵
    private int[][] pre;//保存到达目标顶点的前驱顶点，也是动态变化的

    /**
     * @param len 长度（顶点数）
     * @param matrix 邻接矩阵
     * @param vertex 顶点集
     */
    public Graph(int len,int[][] matrix,char[] vertex) {
        this.vertex = vertex;
        dis = matrix;
        pre = new int[len][len];
        //初始化pre，pre存放的是顶点的下标，并不是顶点
        for (int i = 0; i < len; i++) {
            Arrays.fill(pre[i],i);
        }
    }
    //显示pre数组和dis数组
    public void show(){
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < pre.length; j++) {
                System.out.printf("%12c",vertex[pre[i][j]]);
            }
            System.out.println();
            for (int j = 0; j < dis.length; j++) {
                System.out.printf("(%c -> %c:%2d)  ",vertex[i],vertex[j],dis[i][j]);
            }
            System.out.println();
        }
    }
    //弗洛伊德算法
    public void floyd(){
        int len = 0;//保存距离
        for (int k = 0; k < vertex.length; k++) {//中间顶点的下标
            for (int i = 0; i < vertex.length; i++) {//出发顶点
                for (int j = 0; j < vertex.length; j++) {//到达顶点
                    len = dis[i][k] + dis[k][j];//从i顶点经过k顶点到j顶点的距离
                    if(len < dis[i][j]){//如果经过的距离小于直连的距离，就更新距离
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];//更新前驱顶点
                    }
                }
            }
        }
    }
}