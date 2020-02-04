package Sort;

import java.util.Arrays;

public class QuickSort {//快速排序

    public static void main(String[] args) {
        int[] arr = {9,8,4,71,46,5,4,3,28,1231,0,768};
        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left ,int right){
        int l = left;
        int r = right;
        int pivot = arr[(l + r) / 2];//中轴值
        int temp = 0;
        while (l < r){

            while(arr[l] < pivot){
                l ++;
            }
            while(arr[r] > pivot){
                r--;
            }
            if(l >= r){
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if(arr[l] == pivot){
                r--;
            }
            if(arr[r] == pivot){
                l++;
            }
        }
        if(l == r){
            l++;
            r--;
        }
        //向左递归
        if(left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }
}
