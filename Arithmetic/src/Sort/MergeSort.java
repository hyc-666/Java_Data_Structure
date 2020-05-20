package Sort;

import java.util.Arrays;

public class MergeSort {//归并排序

    //没写好也改不出来了，死龟了
    public static void main(String[] args) {
        int[] arr = new int[]{9,8,7,6,5,4,3,2,1,0};
        System.out.println(Arrays.toString(arr));
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length - 1,temp);
        System.out.println(Arrays.toString(arr));
    }
    //并的方法

    /**
     *
     * @param arr 需要排序的原始数组
     * @param left 左边有序数列的开始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的临时数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp) {
        int i = left;//左边有序数组的初始索引
        int j = mid + 1;//右边有序数组的初始索引
        int t = 0;//中转数组的索引
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //把一方剩余的数据全部填充到temp
        while (i <= mid){
            temp[t] = arr[i];
            t++;
            i++;
        }
        while(j <= right){
            temp[t] = arr[j];
            t++;
            j++;
        }
        //把temp的数据拷贝到arr回去
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        while(left < right){
            int mid = (left + right) / 2;
            //向左递归
            mergeSort(arr,left,mid,temp);
            //向右递归
            mergeSort(arr,mid + 1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }
}
