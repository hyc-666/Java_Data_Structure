package Sort;

import java.util.Arrays;

//基数排序是稳定的排序算法
//本编代码没有针对负数作出处理
public class RadixSort {//基数排序（升级版的桶排序）
    //基数排序是典型的拿空间换时间的排序算法，非常耗费内存
    //排序八千万个数大约需要耗费内存3.3G

    public static void main(String[] args) {

        int[] arr = {53,3,542,748,14,214};
        radixSort(arr);
    }
    //基数排序
    public static void radixSort(int[] arr){
        //需要先确定排序的轮数，先要找到最大的数
        int max = arr[0];//假定第一个就是最大的
        for (int i = 1; i < arr.length; i++) {
            if(max < arr[i]){
                max = arr[i];
            }
        }
        //得到最大的数的位数
        /**
        int times = 0;
        while(max / 10 > 0){
            max++;
            max /= 10;
        }*/
        //让最大数加上一个空串再求出其长度就是最大的位数
        int maxLength = (max + "").length();
        //创建桶排序的二维数组
        //每个桶都是一个一维数组
        for (int i = 0,n = 1; i < maxLength; i++,n *= 10) {
            int[][] bucket = new int[10][arr.length];
            //每个桶里边放了多少个数据用一个一维数组表示
            int[] bucketElementCount = new int[10];//一共10个桶
            int range = 1;
            //把原数组里边的每个数据依据分类放在桶里
            for (int j = 0; j < arr.length; j++) {
                int digts = arr[j] / n % 10;
                bucket[digts][bucketElementCount[digts]] = arr[j];
                bucketElementCount[digts]++;
            }
            //按照桶的顺序依次取出桶里边的数据放入原数组中
            int index = 0;//桶下标
            for (int k = 0; k < bucketElementCount.length; k++) {
                if (bucketElementCount[k] != 0) {
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        //取出第k个桶里的第buce=ketElementCount[k]个数
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //放完以后要把本轮桶中的元素全部清空
                bucketElementCount[k] = 0;
            }
            //System.out.println("第"+ (i + 1) +"轮排序后的结果是："+ Arrays.toString(arr));
        }

    }
}
