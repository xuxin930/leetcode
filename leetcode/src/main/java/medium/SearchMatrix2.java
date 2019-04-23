package medium;

import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode240:搜索二维矩阵2
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     每行的元素从左到右升序排列。
     每列的元素从上到下升序排列。
     现有矩阵 matrix 如下：
     [
         [1,   4,  7, 11, 15],
         [2,   5,  8, 12, 19],
         [3,   6,  9, 16, 22],
         [10, 13, 14, 17, 24],
         [18, 21, 23, 26, 30]
     ]
 给定 target = 5，返回 true。
 给定 target = 20，返回 false。
 */
public class SearchMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean result = false;
        //传入不是矩阵返回false
        if ((matrix==null||matrix.length==0)||(matrix.length>=1 && matrix[0].length==0)){
            return false;
        }
        int root = matrix[matrix.length - 1][0]; //矩阵左下角元素
        if (root < target){
            //防止越界最后一列还没找到返回false
            if (matrix[0].length <=1){
                return false;
            }
            //小于搜索目标,删除这一列
            for (int i = 0; i < matrix.length ; i++) {
                matrix[i] = Arrays.copyOfRange(matrix[i],1,matrix[i].length);
            }
            result = searchMatrix(matrix,target);
        }else if (root > target){
            //防止越界最后一行没找到返回false
            if (matrix.length<=0){
                return false;
            }
            //大于搜索目标,删除这一行
            matrix = Arrays.copyOfRange(matrix,0,matrix.length-1);
            result = searchMatrix(matrix,target);
        }else{
            result = true;
        }
        return result;
    }

    /**
     * 更快速实现
      * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int r = 0, c = cols - 1;
        while (r < rows && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                c--;
            } else {
                r++;
            }
        }

        return false;
    }
    @Test
    public void test(){
        int[] arr = new int[0];
        arr = Arrays.copyOfRange(arr,0,0);
        String s = Arrays.toString(arr);
        System.out.println(s);
    }
}
