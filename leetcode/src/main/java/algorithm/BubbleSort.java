package algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 *  小的上浮,大的沉底,每次少比较一个上轮沉底的数
 *  最坏复杂度n^2
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 4, 8, 4, 1, 2, 54, 4, 1, 2, 5, 7, 1, 2, 4, 4, 1, 58, 56};
        new BubbleSort().sort(arr);        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //最大的沉底,i是沉底的个数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
