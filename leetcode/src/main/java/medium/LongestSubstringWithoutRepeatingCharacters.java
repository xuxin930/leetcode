package medium;

import org.junit.Test;

import java.util.*;

/**
 *  LeetCode 3
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 示例 1:

 输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 示例 2:

 输入: "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:

 输入: "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LongestSubstringWithoutRepeatingCharacters {
    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("abba"));
    }

    /**
     * 快慢指针滑动窗口
     *
     * @param s
     * @return
     */
    int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int result = 0;
        int left = 0; //左指针 慢指针
        int right = 0; //右指针 快指针
        Map<Character, Integer> map = new HashMap<>(); //存储不重复字符最近位置
        //循环开始
        while (right < s.length()) {
            //出现重复字符
            if (map.containsKey(s.charAt(right))) {
                //慢指针向前移动至 出现重复字符的原有位置之后
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            //记录字符出现的位置
            map.put(s.charAt(right), right);
            //记录快慢指针之间的窗口大小是否是想要结果
            result = Math.max(result, right - left + 1);
            //快指针向前滑动
            right++;
        }
        return result;
    }

    /**
     * asc字符代替Map索引
     *
     * @param s
     * @return
     */
    int lengthOfLongestSubstring2(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int result = 0;
        int left = 0; //左指针 慢指针
        int right = 0; //右指针 快指针
        Integer[] charArray = new Integer[128]; //这里一定要用Interger 不能int ;int 默认0 和第0号索引冲突
        //循环开始
        while (right < s.length()) {
            if (charArray[s.charAt(right)] != null) {
                left = Math.max(left, charArray[s.charAt(right)] + 1);
            }
            charArray[s.charAt(right)] = right;
            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }

    /**
     * 队列实现
     *
     * @param s
     * @return
     */
    int lengthOfLongestSubstring3(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int result = 0;
        Queue<Character> q = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (q.contains(c)) {
                while (true) {
                    Character poll = q.poll();
                    if (c.equals(poll)) {
                        break;
                    }
                }
            }
            q.offer(c);
            result = Math.max(result,q.size());
        }
        return result;
    }
}