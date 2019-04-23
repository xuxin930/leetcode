package easy;

import java.util.Arrays;

/**
 * LeetCode88:合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
         示例:
         输入:
         nums1 = [1,2,3,0,0,0], m = 3
         nums2 = [2,5,6],       n = 3
         输出: [1,2,2,3,5,6]
 */
public class MergeTwoArrays {
    /**
     * 把2填充进1,然后排序
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int n1 = m;
        int n2 = 0;
        while (n1<nums1.length || n2<n){
            nums1[n1]=nums2[n2];
            n1++;
            n2++;
        }
        Arrays.sort(nums1);
    }

    /**
     * 执行更快的解答
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int total = m + n - 1;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[total--] = nums1[m - 1];
                m--;
            } else {
                nums1[total--] = nums2[n - 1];
                n--;
            }
        }
        while (m > 0) {
            nums1[total--] = nums1[m - 1];
            m--;
        }
        while (n > 0) {
            nums1[total--] = nums2[n - 1];
            n--;
        }
    }
}
