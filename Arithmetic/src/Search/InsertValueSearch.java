package Search;

public class InsertValueSearch {//插值查找，也需要数组有序
    //类似折半查找的规律，只不过在计算中轴值得时候按照大概位置直接计算出大概在那个所应范围内
    //适合数据比较连续得时候使用
    //也就是数组数据分布比较均匀的时候
    /**
     * 插值查找算法的举例说明
     * 数组arr=[1,2, 3, .... 1PO]
     * 假如我们需要查找的值1
     * 使用二分查找的话，我们需要多次递归，才能找到1
     * 使用插值查找算法
     * int mid= left+ (right - left) * (findVal-arr[left]) / (arr[right] -arr[left])
     * intmid=0+(99-0)* (1- 1)/(100-1)=0+99*0/99=0
     */
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValueSearch(arr,0,arr.length - 1,1));
    }

    /**
     *
     * @param arr 需要进行查找得数组
     * @param left 左边索引
     * @param right 右边索引
     * @param key 需要查找得关键值
     * @return 查找到返回下标，没有查找到返回一个非法下标，即 -1
     */
    public static int insertValueSearch(int[] arr, int left , int right, int key){
        //可以先检测要查找的数据在不在这个数组数据的范围之内
        if(key > arr[arr.length - 1] || key < arr[0]){
            return -1;
        }
        //直接把折半查找得代码拿过来
        while (left < right){
            //只是中轴值的计算方式不一样
            int mid = left + (right - left) * (key - arr[left]) / (arr[right] - arr[left]);
            //数据过大是防止越界
            if(key < arr[mid]){//向左递归
                return insertValueSearch(arr,left,mid - 1,key);
            }else if(key > arr[mid]){//向右递归
                return insertValueSearch(arr,mid + 1,right,key);
            }else{
                return mid;
            }
        }
        return -1;
    }
}
