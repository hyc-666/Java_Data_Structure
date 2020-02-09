package Sort;

import java.util.Arrays;

/**
 * 1).将无序序列构建成一个堆,根据升序降序需求选择大顶堆或小顶堆;
 * 2).将堆顶元素与末尾元素交换，将最大元素沉'到数组末端;
 * 3).重新调整结构,使其满足堆定义,然后继续交换堆顶元素与当前末尾元素,反复执行调整
 * +交换步骤,直到整个序列有序。
 */
public class HeapSort {//堆排序
    /**
     * 其实堆排序是要借助二叉树这种结构，
     * 但是在实际中是把顺序二叉树直接存储在数组里的
     * 二叉树其实只是一个逻辑上的二叉树
     * 整个的操作都是在数组上进行的
     * 但是需要借助顺序二叉树这种结构来进行了逻辑上的处理
     */
    public static void main(String[] args) {
        int[] arr = {45,4564,-6,8,768,-46,50,457,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void heapSort(int[] arr){
        //先调整一次
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        //将堆顶元素与叶子结点交换，将最大元素沉到堆低
        for (int j = arr.length - 1; j > 0 ; j--) {
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
    }

    /**
     *
     * @param arr 需要调整成大顶堆的二叉树，其实是存储在数组里的
     * @param i 从左至右，从下到上的调整，从左侧第倒数第一个非叶子结点开始
     * @param len 每次需要调整的数组长度，这个篡改都调整一轮就减一，直到全部调整完毕
     */
    //调整数组常委一个大顶堆
    public static void adjustHeap(int[] arr,int i,int len){
        int temp = arr[i];//先保存当前需要调整的结点
        //从左子树开始
        for(int k = 2 * i + 1;k < len;k = k * 2 + 1){
            if(k + 1 < len && arr[k] < arr[k + 1]){//比较左右结点的值的大小，让k指向较大的结点
                k++;
            }
            if(arr[k] > temp){//比较子节点和父节点的大小
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
            //for循环结束后将以i结点为父节点的子树调整为一个大顶堆
        }
        arr[i] = temp;
    }
}
