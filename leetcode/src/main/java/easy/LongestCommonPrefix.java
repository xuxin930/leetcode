package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode:14 最常公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * <p>
 * TODO:这个问题满足字典树Trie数据结构,是否可以用Trie求解
 */
public class LongestCommonPrefix {

    /**
     * 我的解法,纵向对齐再一位一位向下找
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        int i = 0;
        outter:
        for (; ; ) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    break outter;
                }
                char c = strs[j].charAt(i);
                set.add(c);
            }
            if (set.size() == 1) {
                result += set.iterator().next();
                i++;
            } else {
                break;
            }
        }
        return result;
    }
}
