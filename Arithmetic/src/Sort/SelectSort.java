package Sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1,0};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr){
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];//假设这是最小值
            for (int j = i + 1; j < arr.length; j++) {
                if(min > arr[j]){
                    min = arr[j];
                    minIndex = j;//标记最小值的下标位置，下面交换要用到
                }
            }
            //交换最小值下标位置上的元素，即最小值与当前位置的元素交换
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }
}
