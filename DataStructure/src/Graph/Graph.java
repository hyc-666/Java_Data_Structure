package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    public static void main(String[] args) {
//        Graph graph = new Graph(5);
//        String[] vertexs = {"A","B","C","D","E"};
        Graph graph = new Graph(8);
        String[] vertexs = {"1","2","3","4","5","6","7","8"};
        //换一个图测试
        for (String vertex : vertexs){//添加入结点
            graph.insertVertex(vertex);
        }
        //只能通过手动的方法添加边的关系
//        graph.insertEdges(0,1,1);
//        graph.insertEdges(0,2,1);
//        graph.insertEdges(1,2,1);
//        graph.insertEdges(1,3,1);
//        graph.insertEdges(1,4,1);
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.insertEdges(3, 7, 1);
        graph.insertEdges(4, 7, 1);
        graph.insertEdges(2, 5, 1);
        graph.insertEdges(2, 6, 1);
        graph.insertEdges(5, 6, 1);

        //显示邻接矩阵
        graph.showGraph();
        //测试图的升读优先遍历
        System.out.println("图的深度优先遍历");
        graph.deepFirstSearch();
        //需要单独测试，否则一遍深度优先以后isVisited全部都置true了
        //改进方法是吧这个isVisited的初始化放在在调用遍历的时候，这样每次进行遍历的时候就会重置isVisiyed
        System.out.println();
        System.out.println("广度优先测试:");
        graph.boardFirstSearch();
    }

    private ArrayList<String> vertexList;//保存图的顶点的集合
    private int[][] edges;//用图的邻接矩阵保存图的边
    private int numberOfEdges;//表示一共有多少条边
    //图的深度优先遍历，定义一个boolean数组用来存储顶点是否被访问
    private boolean[] isVisited;
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
    }

    /**
     *  找到一个顶点的第一个邻接的顶点
     * @param index 某个顶点的下标
     * @return 如果存在邻接的顶点则返回这个邻接点的下标，没有找到则但会-1
     */
    public int getFirstNeibor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标来获取下一个邻接结点的下标或者是否存在
    public int getNextNeibor(int v1,int v2){
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }
    /**
     *图的深度优先搜索(Depth First Search)。
     * 1)深度优先遍历， 从初始访问结点出发，初始访问结点可能有多个邻接结点，深度优先遍历的策略就是首先访问
     * 第一个邻接结点，然后再以这个被访问的邻接结点作为初始结点，访问它的第一个邻接结点， 可 以这样理解:
     * 每次都在访问完当前结点后首先访问当前结点的第一一个邻接结点。
     * 2)我们可以看到，这样的访间策略是优先往纵向挖掘深入，而不是对一-个结点的所有邻接结点进行横向访问。
     * 3)显然， 深度优先搜索是一个递归的过程
     */
    //从第一个结点开始，i第一次是0
    private void deepFirstSearch(boolean[] isVisited, int i){
        //访问第一个结点并输出，并且标记已访问
        System.out.print(getVertex(i) + "->");
        isVisited[i] = true;
        //查找当前结点的第一个邻接结点
        int w = getFirstNeibor(i);
        while (w != -1){//说明存在
            if (!isVisited[w]){//如果该节点没有被访问过
                deepFirstSearch(isVisited,w);
            }
            w = getNextNeibor(i,w);
        }
    }

    /**
     * 深度优先遍历算法步骤
     * 访问初始结点v，并标记结点v为已访间。
     * 查我结点v的第- -个邻接结点w。
     * 若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个结点继续。
     * 若w未被访问，对w进行深度优先遍历递归(即把w当做另一个v，然后进行步骤123)。
     * 查找结点v的w邻接结点的下一-个邻接结点，转到步骤3。
     */
    //对dfs重载，需要把所有的顶点都进行依次遍历，也是为了遍历不连通图
    public void deepFirstSearch(){
        isVisited = new boolean[getNumberOfVertex()];
        //遍历所有结点进行回溯
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if(!isVisited[i]) {
                deepFirstSearch(isVisited, i);
            }
        }
    }

    /**
     * 图的广度优先搜索(Broad First Search)。
     * 类似于一个分层搜索的过程，广度优先遍历需要使用一个队列以保持访问过的结点的顺序，以便按这个顺序来访问
     * 这些结点的邻接结点
     * 广度优先遍历算法步骤;
     * 访问初始结点v并标记结点v为已访问。
     * 结点v入队列
     * 当队列非空时，继续执行，否则算法结束。
     */
    //对一个节点的广度优先遍历
    private void boardFirstSearch(boolean[] isVisited,int i){
        int u;//表示队列的一个头节点，获得第一个邻接结点
        //实际上，这里取出的是结点的下标，并不是真的取出结点，结点是按下表存储在邻接矩阵的
        int w;
        //记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问并输出
        System.out.print(getVertex(i) + "->");
        //标记为已经访问
        isVisited[i] = true;
        queue.addLast(i);
        while (queue.isEmpty()){
            //取出队列的头下标
            u = (Integer)queue.removeFirst();
            //得到第一个邻接点的下标
            w = getFirstNeibor(u);
            while (w != -1){//存在
                if(!isVisited[w]){//检测是否已经被访问
                    System.out.print(getVertex(w) + "->");
                    isVisited[w] = true;
                    //已经访问，入队列
                    queue.addLast(w);
                }
                //寻找下一个邻接结点
                w = getNextNeibor(u,w);
            }
        }
    }

    /**
     *广度优先遍历算法步骤
     * 1)访问初始结点v并标记结点v为已访问。
     * 2)结点v入队列 |
     * 3)当队列非空时，继续执行，否则算法结束。
     * 4)出队列，取得队头结点u。
     * 5)查找结点u的第一个邻接结点w。
     * 6) 若结点u的邻接结点w不存在，则转到步骤3;否则循环执行以下三个步骤:
     * 6.1若结点w尚未被访问，则访问结点w并标记为已访问。
     * 6.2结点w入队列
     * 6.3查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6。
     */
    //重载bfs广度优先
    public void boardFirstSearch(){
        isVisited = new boolean[getNumberOfVertex()];
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if(!isVisited[i]){
                boardFirstSearch(isVisited,i);
            }
        }
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