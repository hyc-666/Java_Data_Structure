package Sort;

import java.util.Arrays;

public class BubbleSort {//冒泡排序（带优化）

    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1,0};
        bubbluSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    //冒泡排序的优化
    /**
    * 优化方法是增加一个标志量flag，
    * 假如在某一趟排序中一次交换都没有
    * 那么说明数列已经有序，则可以提前退出循环
    * */
    public static void bubbluSort(int[] arr){
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(!flag){
                break;
            }else{
                flag = false;
            }
        }
    }
}
