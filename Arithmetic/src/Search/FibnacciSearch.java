package Search;

import java.util.Arrays;

/**
 * 斐波那契（黄鸡分割）查找算法类似前边二分查找一样，也是需要数组有序
 * 只不过在进行数组分割的时候不是折半分割的，而是使用黄金分割来分割这个数组的
 * 需要引入一个斐波那契数列只是便于进行分割
 */

public class FibnacciSearch { //斐波那契（黄金分割）查找算法

    //需要得到一个斐波那契数列
    public static int maxSize  = 20;

    public static void main(String[] args) {
        int [] arr = {1,8,9,10,89,1000,1234};
        System.out.println(fibnaciiSearch(arr,20));

    }

    /**
     *
     * @return 用这个函数得到一个斐波那契数列
     */
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     *
     * @param arr 需要在此数组中查找
     * @param key 需要查找的关键值
     * @return 如果查找到返回对应的下标，没有查找到则返回一个非法的下标，即 -1
     */
    public static int fibnaciiSearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//斐波那契分割的下标
        int mid = 0;
        int[] f = fib();
        while (high > f[k] - 1){
            k++;
        }
        //f[k] 有可能会大于数列的长度，所以需要把原数组拷贝到新数组，
        //不足的地方用0填充
        int[] temp = Arrays.copyOf(arr,f[k]);
        //不足的地方实际上使用原数组的最后一个数填充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        while (low <= high){
            mid = low + f[k - 1] - 1;
            if(key < temp[mid]){
                high = mid - 1;//想左边查找
                k--;//
            }else if(key > temp[mid]){
                low = mid + 1;
                k -= 2;
            }else{
                if(low <= high){
                    return low;
                }else{
                    return high;
                }
            }

        }
        return -1;
    }
}
