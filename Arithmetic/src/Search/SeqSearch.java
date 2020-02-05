package Search;

import java.util.Arrays;

public class SeqSearch {
    //顺序查找，也叫线性查找，逐一比对，比较简单，一趟遍历就可以完成
    public static void main(String[] args) {
        int[] arr = new int[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*100);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(seqSearch(arr,15));
    }
    public static int seqSearch(int[] arr,int key){
        //查找到返回下标，没有找到返回-1
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == key){
                return i;
            }
        }
        return -1;
    }
}
