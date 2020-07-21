package algorithm;

import java.util.Arrays;

/**
 * 选择排序
 *   一直选最小的放在未排序序列的第一个  最差的时间复杂度n^2
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 4, 8, 4, 1, 2, 54, 4, 1, 2, 5, 7, 1, 2, 4, 4, 1, 58, 56};
        new SelectionSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
