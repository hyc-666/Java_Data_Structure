package Kruskal;

/**
 * 克鲁斯卡尔算法求最小生成树，主要有两步：
 * 1、对所有的边按照权值从小到大排序
 *   从小到大依次把这些边加入到最小生成树，
 *  如果新待加入的边与已有最小生成树加入后构成回路则不加入
 * 2、判断回路，方法是看待加入的边的两个顶点是否有在已加入的最小生成树中是否有相同终点
 *  终点是指按照顺序看最大的那个顶点
 */

//克鲁斯卡尔算法
public class Kruskal {
    /** 还是看具体问题：
     *1)有北京有新增7个站点(A,B,C,D,E,F,G)， 现在需要修路把7个站点连通
     * 2)各个站点的距离用边线表示(权)，比如A-B距离12公里
     * 3) 问:如何修路保证各个站点都能连通，并且总的修建公路总里程最短?
     */
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };
        Kruskal kruskal = new Kruskal(vertex,matrix);
        kruskal.show();
        Edge[] edges = kruskal.getEdges();
        kruskal.EdgeSort(edges);
        System.out.println("排序后：");
        for (int i = 0; i < edges.length; i++) {
            System.out.println(edges[i]);
        }
        kruskal.kruskal();
    }

    private int edgesNum; //边的个数
    private char[] vertex; //存放顶点信息
    private int[][] matrix;// 图的邻接矩阵
    private static final int INF = Integer.MAX_VALUE;//以这个数来表示两个点不连通（不直连）

    public Kruskal(char[] vertex, int[][] matrix) {
        int vlen = vertex.length;
        //初始化顶点,赋值这个顶点集，让它独立
        this.vertex = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertex[i] = vertex[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的数量
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if(this.matrix[i][j] != INF){
                    edgesNum++;
                }
            }
        }
    }
    //打印邻接矩阵
    public void show(){
        System.out.println("图的邻接矩阵：");
        System.out.printf("%12c",' ');
        for (int i = 0; i < vertex.length; i++) {
            System.out.printf("%12c", vertex[i]);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%12c",vertex[i]);
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }
    //对边进行排序
    private void EdgeSort(Edge[] edges){
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if(edges[j].weight > edges[j + 1].weight){
                    Edge temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 得到顶点的对应下标
     * @param ch 传进的顶点
     * @return 返回顶点下标,找不到返回下标
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertex.length; i++) {
            if(vertex[i] == ch){
                return i;
            }
        }
        return  - 1;
    }

    public void kruskal(){
        int index = 0;
        int ends[] = new int[edgesNum];
        //创建最小生成树的数组
        Edge[] res = new Edge[edgesNum];
        //获取所有边的集合
        Edge[] edges = getEdges();
        //按照边的权值大小排序
        EdgeSort(edges);
        //将边从小到大添加到最小生成树中，并判断是否形成回路,没有就加入res
        for (int i = 0; i < edges.length; i++) {
            //获得第i条边的两个顶点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            //判断边的两个顶点的终点是否相同
            int m = getEnds(ends,p1);
            int n = getEnds(ends,p2);
            //判断是否构成回路
            if (m != n){
                ends[m] = n;//设置m在已有最小生成树中的终点
                res[index++] = edges[i];
            }
        }
        //统计并打印最小生成树
        System.out.println("最小生成树：");
//        System.out.println(Arrays.toString(res));
        for (int i = 0; i < index; i++) {
            System.out.println(res[i]);
        }
    }
    /**
     * 得到图中的边的集合
     * 通过matrix获取
     * @return 返回一个Edge数组
     */
    private Edge[] getEdges(){
        int index = 0;
        Edge[] edges = new Edge[this.edgesNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if(matrix[i][j] != INF){
                    edges[index++] = new Edge(vertex[i],vertex[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取以下标为i的顶点的终点。用于判断后面判断两个顶点是否有相同终点
     * @param ends 记录了各个顶点对应的终点，这个数组是跟随边的加入而变化的
     * @param i 下标
     * @return 返回顶点的终点
     */
    private int getEnds(int[] ends,int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}
//创建一个类，表示边
class Edge{
    char start;//边的两个顶点
    char end;
    int weight;//边的权值

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<" + start +
                "," + end +
                ">weight=" + weight ;
    }
}