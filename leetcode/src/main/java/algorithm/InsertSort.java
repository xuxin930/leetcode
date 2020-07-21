package algorithm;

import java.util.Arrays;

/**
 * 插入排序
 *  将一个记录插入到已经排好序的有序序列中,得到一个新的+1长度的有序序列
 *  最坏复杂度n^2
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 6, 1, 2};
        new InsertSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr) {
        int i, j;
        //无序队列
        for (i = 1; i < arr.length; i++) {
            int temp = arr[i];
            //向有序队列插入
            for (j = i - 1; j >= 0; j--) {
                if (temp > arr[j]) {
                    break;
                } else {
                    arr[j + 1] = arr[j];
                }
            }
            //插入temp位置
            arr[j + 1] = temp;
        }
    }
}
