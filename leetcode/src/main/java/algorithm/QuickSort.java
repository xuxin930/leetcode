package algorithm;

import java.util.Arrays;

/**
 * 快排
 *  分界,一边小一边大,递归
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 4, 8, 4, 1, 2, 54, 4, 1, 2, 5, 7, 1, 2, 4, 4, 1, 58, 56};
        new QuickSort().sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    private void sort(int[] arr, int l, int r) {
        if (l >= r) return;
        int left = l;
        int right = r;
        int key = arr[l];//最左为key
        while (left < right) {
            //找右边的
            while (right > left && arr[right] >= key) {
                right--;
            }
            //找左边的
            while (left < right && arr[left] <= key) {
                left++;
            }
            //左右值互换
            if (left < right) {
                swap(arr, left, right);
            }
        }
        //key值归位
        arr[l] = arr[left];
        arr[left] = key;
        //递归
        sort(arr, l, left - 1);
        sort(arr, right + 1, r);
    }

    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
