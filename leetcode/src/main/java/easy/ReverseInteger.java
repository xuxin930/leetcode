package easy;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  LeetCode:7
 *给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

 示例 1:

 输入: 123
 输出: 321
  示例 2:

 输入: -123
 输出: -321
 示例 3:

 输入: 120
 输出: 21
 注意:

 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class ReverseInteger {
    @Test
    public void test() {
        System.out.println(reverse(-1563847412));
    }


    public int reverse2(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        return (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) ? 0 : (int) result;
    }

    public int reverse(int x) {
        boolean flag = x < 0;
        if (x <= Integer.MIN_VALUE) {
            return 0;
        }
        x = Math.abs(x);
        Queue<Integer> q = new LinkedList<>();
        while (x >= 10) {
            q.offer(x % 10);
            x = x / 10;
        }
        q.offer(x);
        String result = "";
        while (!q.isEmpty()) {
            result += q.poll();
        }

        if (flag) {
            result = "-" + result;
        }
        long l = Long.parseLong(result);
        return (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) ? 0 : (int) l;
    }
}
