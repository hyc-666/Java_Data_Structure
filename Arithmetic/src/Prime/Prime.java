package Prime;
//普利姆算法

import java.util.Arrays;

/**
 * 问题场景：
 * 有七个村庄(A, B, C,D,E,F,G)要修路将所有村庄都连通，各个村庄之间的距离由图解给出
 * 各个村庄的距离用边线表示(权)，比如A - B距离5公里
 * 问:如何修路保证各个村庄都能连通，并且总的修建公路总里程最短?
 * 思路:将10条边，连接即可，但是总的里程数不是最小
 * 正确的思路，就是尽可能的选择少的路线，并且每条路线最小，保证总里程数最少.
 * 设计应该修通哪些路
 */
public class Prime {
    /**
     * 普利姆(Prim)算法求最小生成树， 也就是在包含n个顶点的连通图中，
     * 找出只有(n-1)条边包含所有n个顶点的连通子图，也就是所谓的极小连通子图
     * 普利姆的算法如下:
     * 1) 设G=(V,E)是连通网， T=(U,D)是最小生成树，V,U是顶点集合，E.D是边的集合
     * 2)若从顶点u开始构造最小生成树，则从集合V中取出顶点u放入集合U中，标记项点v的visited[u]=1
     * 3)若集合U中顶点ui 与集合V-U中的顶点vj之间存在边，则寻找这些边中权值最小的边，
     *   但不能构成回路，将顶点vj加入集合U中，将边(ui,vj) 加入集合D中，标记visited[vj]=1
     * 4)重复步骤②，直到U与V相等，即所有顶点都被标记为访问过，此时D中有n-1条边
     * 5) 提示:单独看步骤很难理解，代码写出来就好理解了
     */
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int vertex = data.length;
        int weight[][] = {  //表示村庄的边的连通情况
                //注意这里不能用0表示不连通，用一个很大的数表示不连通(不直连)
                //不然在计算权的时候会误把0作为最小权值
                {10000,5,7, 10000 , 10000, 10000,2},
                {5, 10000, 10000,9,10000, 10000,3},
                {7, 10000 , 10000, 10000, 8, 10000, 10000},
                {10000,9 , 10000, 10000, 10000,4,10000} ,
                {10000, 10000,8, 10000 ,10000,5,4},
                {10000, 10000 , 10000,4, 5, 10000,6},
                {2, 3, 10000, 10000,4, 6, 10000}};
        MGraph graph = new MGraph(vertex);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertex,data,weight);
        minTree.showGraph(graph);
        //测试普利姆算法
        System.out.println("测试普利姆算法:");
        minTree.prime(graph,0);
    }
}
//最小生成树
class MinTree{
    /**
     * 创建图的邻接矩阵
     * @param graph 传进来的图
     * @param vertex 图的顶点数
     * @param data 顶点信息
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int vertex,char[] data,int[][] weight){
        for (int i = 0;i< vertex; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 普利姆算法得到最小生成树
     * @param graph 图
     * @param v 起始顶点
     */
    public void prime(MGraph graph,int v){
        int[] visited = new int[graph.vertex];//标记顶点是否被当问过.默认全0，表示还没有被访问
        visited[v] = 1;//标记已经访问
        int h1 = -1;
        int h2 = -1;
        int i = 0,j = 0,k;
        int minWeight = Integer.MAX_VALUE;//初始权值无限大，后续会被替换
        for (k = 1; k < graph.vertex; k++) {//循环从1开始，因为算法结束后，边一共有vertex - 1条
            //每次生成的子图和那个结点的距离最近（权值最小）
            for (i = 0; i < graph.vertex; i++) {//i指向一个已经被访问过的结点
                for (j = 0; j < graph.vertex; j++) {//j指向一个还没有被访问过的结点
                    //把这个已经访问过的边和没有访问过的边比较权值，把权值替换为一个更小的
                    if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值："+ minWeight);
            //已经访问过的结点标记为已经访问
            visited[h2] = 1;
            minWeight = Integer.MAX_VALUE;

        }

    }
}
//图类
class MGraph{
    int vertex;//顶点个数
    char[] data;//顶点信息，也就是顶点怎么表示
    int[][] weight;//表示图的边的邻接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}
