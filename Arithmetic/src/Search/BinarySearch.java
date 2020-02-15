package Search;

import com.sun.javafx.image.IntPixelGetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {//二分查找，也叫折半查找

    //二分查找要求数组有序
    //本篇使用递归的方法实现二分查找
    public static void main(String[] args) {
        int[] arr = new int[15];
        //随机生成一个数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*100);
        }
        //利用前面的排序算法对这个数组排序
        Sort.RadixSort.radixSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySerach(arr,0,arr.length,15));

    }
    //二分查找
    //递归的二分查找，返回所有等于给定值的下标
    public static int binarySerach(int[] arr,int left ,int right,int key){
        while (left < right){
            int mid = left + ((right - left) >>> 1);
            if(key < arr[mid]){//向左递归
                return binarySerach(arr,left,mid - 1,key);
            }else if(key > arr[mid]){//向右递归
                return binarySerach(arr,mid + 1,right,key);
            }else{
                return mid;
            }
        }
        return -1;
    }
    //二分查找（非递归）
    public static int binary_Search(int[] arr,int key){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = left + ((right - left) >>> 1);
            if(key == arr[mid]){
                return mid;
            }else if(key < arr[mid]){ //在左半部分查找
                right = mid - 1;
            }else { //在右半部分查找
                left = mid + 1;
            }
        }
        //没有查找到返回 -1
        return -1;
    }
}
