package Horse;
//马踏棋盘算法
//使用贪心算法优化

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 1)马踏棋盘算法也被称为骑士周游问题
 * 2)将马随机放在国际象棋的8X8棋盘Board[0~ 7][0~ 7]的某个方格中，
 * 马按走棋规则(马走日字)进行移动。要求每个方格只进入一次，走遍棋盘上全部64个方格
 */
public class Horse {
    //递归加回溯法，测试时间为：36925ms
    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;//初始位置的行
        int col = 1;//初始位置的列
        int[][] chessBoard = new int[X][Y];//棋盘
        visited = new boolean[X * Y];
        //测试马踏棋盘
        System.out.println("测试耗时:");
        long start = System.currentTimeMillis();
        travel(chessBoard,row - 1,col - 1,1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "ms");
        System.out.println("输出棋盘的最后情况");
        for(int[] rows : chessBoard){
            for(int step : rows){
                System.out.printf("%2d  ",step);
            }
            System.out.println();
        }
    }
    private static int X;//注意x表示列
    private static int Y;//y表示行
    private static boolean[] visited;//标记棋盘每个位置是否被访问过的点
    private static boolean finished;//标记棋盘是否全部被访问

    /**
     * 完成马踏棋盘问题
     * @param chessBoard 棋盘
     * @param row 当前行
     * @param col 当前列
     * @param step 第几步，初始位置是1
     */
    public static void travel(int[][] chessBoard,int row,int col,int step){
        chessBoard[row][col] = step;
        visited[row * X + col] = true;//标记该位置已经被访问，这里用一维数组表示二维数组的顺序
        ArrayList<Point> ps = next(new Point(col,row));
        //对下一步的可能性数量排序
        sort(ps);
        while (!ps.isEmpty()){
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断该点是否可走
            if(!visited[p.y * X + p.x]){//说明该点可走
                travel(chessBoard,p.y,p.x,step + 1);
            }
        }
        //判断是否完成
        if(step < X * Y && !finished){//没有完成就要回溯
            chessBoard[row][col] = 0;
            visited[row * X + col] = false;
        }else{
            finished = true;
        }
    }

    /**
     * 记录从当前这个点出发还可以走哪些位置
     * @param curPoint 当前位置
     * @return 返回一个List
     */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0){//表示可以走5(x-2,y1)这个点而不越界
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0){//表示可以走6(x-1,y-2)这个点而不越界
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0){//表示可以走7(x+1,y-2)这个点而不越界
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0){//表示可以走0(x+2,y-1)这个点而不越界
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y){//表示可以走1(x+2,y+1)这个点而不越界
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y){//表示可以走2(x+1,y+2)这个点而不越界
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y){//表示可以走3(x-1,y+2)这个点而不越界
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y){//表示可以走4(x-2,y+1)这个点而不越界
            ps.add(new Point(p1));
        }
        return ps;
    }
    //使用贪心算法优化
    //根据当前这一步的结果对下一步的所有结果排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取o1的下一步的所有位置的数量
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if(count1 < count2){
                    return -1;
                }else if(count1 == count2) {
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }

}
