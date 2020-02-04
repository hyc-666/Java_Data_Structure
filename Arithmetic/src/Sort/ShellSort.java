package Sort;

import java.util.Arrays;

public class ShellSort {//希尔排序
    //步骤是先将数据分组，每组进行直接插入排序，然后知道分组越来越小
    public static void main(String[] args) {
        int[] arr = {9,8,37,6,15,4,31,2,11,0};
        shell_Sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void shellSort(int[] arr){
        //交换法
        //将数据分组
        System.out.println("排序过程：");
        for(int step = arr.length / 2;step > 0;step /= 2){
            for (int i = step; i < arr.length ; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if(arr[j] > arr[j + step]){
                        int temp = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }
    //修改希尔排序为移位法
    public static void shell_Sort(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                while(j - gap >= 0 && temp < arr[j - gap]){
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            System.out.println(Arrays.toString(arr));
        }
    }
}
