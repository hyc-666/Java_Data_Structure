package Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<String> vertexList;//保存图的顶点的集合
    private int[][] edges;//用图的邻接矩阵保存图的边
    private int numberOfEdges;//表示一共有多少条边

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexs = {"A","B","C","D","E"};
        for (String vertex : vertexs){//添加入结点
            graph.insertVertex(vertex);
        }
        //只能通过手动的方法添加边的关系
        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);
        //显示邻接矩阵
        graph.showGraph();
    }

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);

    }
    //插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //返回图中的结点的个数
    public int getNumberOfVertex(){
        return vertexList.size();
    }
    //返回边的个数
    public int getNumberOfEdges(){
        return numberOfEdges;
    }
    //返回顶点
    public String getVertex(int i){
        return vertexList.get(i);
    }
    //返回v1v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图所对应的矩阵
    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }
    //添加边
    /**
     * 下标表示第几个顶点
     * @param v1 表示第一个顶点的下标
     * @param v2 第二个顶点的下标
     * @param weight 表示边的权值
     */
    public void insertEdges(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdges++;
    }
}