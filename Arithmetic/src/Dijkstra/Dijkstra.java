package Dijkstra;
//迪杰斯特拉算法计算最短路径
/**
 * 迪杰斯特拉(Dijkstra)算法是典型最短路径算法，用于计算一个结点到其他结点的最短路径。
 * 它的主要特点是以起始点为中心向外层层扩展(广度优先搜索思想)，直到扩展到终点为止。
 */
import java.util.Arrays;

public class Dijkstra {
    /**
     *场景：
     *1)战争时期， 胜利乡有7个村庄(A,B,C,D,E,F,G)， 现在有六个邮差，从G点出发，
     * 需要分别把邮件分别送到A,B,C,D,E,F六个村庄
     * 2)各个村庄的距离用边线表示(权)，比如A-B距离5公里
     * 3) 问:如何计算出G村庄到其它各个村庄的最短距离?
     * 4)如果从其它点出发到各个点的最短距离又是多少?
     */
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[ ]{7,N,N,N,8,N,N};
        matrix[3]=new int[ ]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertex,matrix);
        System.out.println("图的邻接矩阵");
        graph.showGraph();
        graph.dijkstra(2);

    }
}
//图类
class Graph{
    private char[] vertex; //顶点
    private int[][] matrix;  //邻接矩阵
    VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }
    //显示图
    public void showGraph(){
        for (int[] link : matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //迪杰斯特拉算法实现
    /**
     * @param index 出发顶点
     */
    public void dijkstra(int index){
        System.out.print("出发点：" + vertex[index]);
        vv = new VisitedVertex(vertex.length,index);
        update(index);//跟新index顶点到其他顶点的距离和其他顶点的前驱节点
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();//选择并返回新的访问结点
            update(index);//跟新index顶点到其他顶点的距离和其他顶点的前驱节点
        }
        //vv.show();
        System.out.println(" 到其他顶点的最短路径：");
        for (int i = 0; i < vertex.length; i++) {
            System.out.println("->" + vertex[i] + "最短路径:"+ vv.dis[i]);
        }
    }
    /**
     * @param index 跟新index下标顶点到其他顶点的距离和其他顶点的前驱顶点
     */
    private void update(int index){
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            //len是从出发点到index顶点的距离+从index到其他顶点的距离
            len = vv.getDis(index) + matrix[index][i];
            //如果i这个顶点还没有访问过并且len的值小于从出发顶点直接到i这个顶点的距离，
            //就需要跟新出发点到i这个顶点的距离
            if(!vv.isVisited(i) && len < vv.getDis(i)){
                vv.updatePre(i,index);//跟新i的前驱结点为index
                vv.updateDis(i,len);//跟新出发点到i这个顶点的距离
            }
        }
    }
}
class VisitedVertex{
    public int[] already_arr;//记录各个顶点是否被访问过，0表示没有，1表示已经访问过
    public int[] pre_visited;//记录每访问一个结点，其他结点的前驱节点
    public int[] dis;// 记录触发顶点到所有结点距离，会动态更新

    /**
     * 构造器
     * @param len 顶点个数
     * @param index 开始顶点的下标
     */
    public VisitedVertex(int len,int index) {
        already_arr = new int[len];
        pre_visited = new int[len];
        dis = new int[len];
        //初始化dis数组，全部初始化为不可达（65535）
//        for (int i = 0; i < dis.length; i++) {
//            dis[i] = 65535;
//        }
        already_arr[index] = 1;//将出发顶点标记为已经访问
        Arrays.fill(dis,65535);
        //设置触发顶点的访问距离为0
        dis[index] = 0;
    }

    /**
     * 判断某个顶点是否已经被访问过
     * @param index 顶点下标
     * @return 访问过是1，返回true，否则返回false
     */
    public boolean isVisited(int index){
        return already_arr[index] == 1;
    }

    /**
     * 跟新出发顶点到index顶点的距离
     * @param index 出发顶点到index顶点
     * @param len 出发顶点到index顶点的距离
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 跟新pre顶点的前驱顶点为index
     * @param pre 需要跟新的顶点
     * @param index  当前顶点的前驱顶点
     */
    public void updatePre(int pre,int index){
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index 顶点下标
     * @return 返回距离
     */
    public int getDis(int index){
        return dis[index];
    }
    //继续选择并返回新的访问结点，比如G访问完以后就是A，A就是新的访问结点（注意不是出发顶点）
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //跟新这个顶点被访问过
        already_arr[index] = 1;
        return index;
    }
    //显示最后的结果
    public void show(){
        System.out.println("=================================");
        System.out.println("已经访问结果:");
        System.out.println(Arrays.toString(already_arr));
        System.out.println("每个顶点的前驱结点;");
        System.out.println(Arrays.toString(pre_visited));
//        System.out.println("出发点到其他顶点的最短路径：");
//        System.out.println(Arrays.toString(dis));
    }
}