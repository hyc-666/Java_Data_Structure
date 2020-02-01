package SparseArray;
//稀疏数组
public class SparseArray {
    public static void main(String[] args) {
        //1.创建原始数组
        int[][] array1 = new int[11][11];
        array1[1][2] = 1;
        array1[2][3] = 2;
        //打印原来的数组
        System.out.println("这是原来的数组：");
        for (int i = 0; i < 11;i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf(" %4d ",array1[i][j]);
            }
            System.out.println();
        }
        //2.创建稀疏数组
        //遍历原数组得到有效数据
        int sum = 0;
        for (int i = 0; i < 11;i++) {
            for (int j = 0; j < 11; j++) {
                if (array1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //创建稀疏数组
        int[][] sparseArray = new int[sum +1][3];
        //第一行保存原始数组的行列值和有效数据个数
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //把有效数据放入稀疏数组
        int count = 0;
        for (int i = 0; i < 11;i++) {
            for (int j = 0; j < 11; j++) {
                if (array1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array1[i][j];
                }
            }
        }
        //保存时可以把这个稀疏数组保存起来，节省空间
        //然后打印这个稀疏数组
        System.out.println("这是稀疏数组：");
        for (int i = 0; i < sum + 1; i++) {
            System.out.printf("%4d%4d%4d",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
            System.out.println();
        }
        //从稀疏数组恢复到原来的数组
        //先读取稀疏数组的第一行以创建原始数组
        int row = sparseArray[0][0];
        int col = sparseArray[0][1];
        int array2[][] = new int[row][col];
        //把稀疏数组中的值写到待恢复的数组中
        for (int i = 1; i < sparseArray.length; i++) {
            array2[sparseArray[i][1]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //打印恢复以后的数组
        System.out.println("这是恢复以后的数组：");
        for (int i = 0; i < 11;i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf(" %4d ",array2[i][j]);
            }
            System.out.println();
        }
    }
}
