package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort { //插入排序

    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1,0};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        //关于时间
        Date date = new Date();//获得当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//把当前时间格式化
        String dateStr = simpleDateFormat.format(date);//把格式化后的时间转成字符串
        System.out.println("当前时间是:" + dateStr);
    }

    public static void insertSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int insertValue = arr[i + 1];//待出入的值
            int insertIndex = i;//待插入的下标
            while(insertIndex >= 0 && insertValue < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;
        }
    }
}
