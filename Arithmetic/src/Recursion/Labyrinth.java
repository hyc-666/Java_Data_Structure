package Recursion;

//回溯法和递归解决迷宫问题
public class Labyrinth {//迷宫问题

    static int ROW = 7;//初始化迷宫的行和列
    static int COL = 8;

    public static void main(String[] args) {
        //初始化一个迷宫
        //1表示墙，0表示可走
      int[][] map = new int[ROW][COL];
        for (int i = 0; i < COL; i++) {//给列赋值，行不变
            map[0][i] = 1;
            map[ROW - 1][i] = 1;
        }
        for (int i = 0; i < ROW; i++) {//给行赋值，列不变
            map[i][0] = 1;
            map[i][COL - 1] = 1;
        }
        //把地图加难一点

        map[1][3] = 1;
        map[2][5] = 1;
        map[3][2] = 1;
        map[3][3] = 1;
        map[3][5] = 1;
        map[4][4] = 1;
        map[5][2] = 1;
        map[5][4] = 1;
        //输出当前迷宫的地图
        System.out.println("这是迷宫");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.printf("%3d",map[i][j]);
            }
            System.out.println();
        }
        //测试一下
        //map是引用类型，操作的都是同一张地图
        setWay(map,1,1);
        //然后打印出路径
        System.out.println("这是走完以后标注的迷宫");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.printf("%3d",map[i][j]);
            }
            System.out.println();
        }
    }
    //寻找路径
    //map是当前迷宫的地图
    //i和j表示出发位置
    //从左上角(1,1)出发,到右下角表示找到出路(ROW - 2,COL - 2)
    //0表示没有走过，1表示墙，2表示可以走，3表示此点已经探测过走不通
    //探测方向顺序是 下->右->上->左
    public static boolean setWay(int[][] map,int i,int j){

        //如果右下角的值(ROW - 2,COL - 2)的值变为2，表示通路已经找到.
        if(map[ROW - 2][COL - 2] == 2){
            //也可以在这里修改终点，可以让方法带进来
            return true;
        }else{
            if(map[i][j] == 0){//该点还没有走过
                map[i][j] = 2;//当前点置2,假定该点能走通
                if(setWay(map,i + 1,j )){//向下走
                    return true;
                }else if(setWay(map,i,j + 1)){//向右走
                    return true;
                }else if(setWay(map,i - 1,j)){//向上走
                    return true;
                }else if(setWay(map,i,j - 1)){//向左走
                    return true;
                }
                else{//表示此路不通
                    map[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }
        }
    }
}
