package medium;

/**
 * LeetCode:5 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * TODO:Manacher算法补充
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring b = new LongestPalindromicSubstring();
        System.out.println(b.longestPalindrome("bb"));
    }

    /**
     * 中心扩散
     *
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        String res = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String s1 = centerSpread(s, i, i);
            String s2 = centerSpread(s, i, i + 1);
            String max = s2.length() > s1.length() ? s2 : s1;
            res = res.length() > max.length() ? res : max;
        }
        return res;
    }

    public String centerSpread(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left + 1, right);
    }

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int len = s.length();
        int begin = 0;
        int maxLen = 0;
        boolean[][] dp = new boolean[len][len]; //dp[i][j] 表示s[i到j]闭区间是否为回文串
        char[] chars = s.toCharArray();
        //初始状态,独立为回文
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        //状态转移
        // dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    begin = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    //暴力匹配
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        String result = s.charAt(0) + "";
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 2; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (isP(substring)) {
                    result = substring.length() > result.length() ? substring : result;
                }
            }

        }
        return result;
    }

    //是否为回文串
    public boolean isP(String s) {
        boolean result = true;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            char aChar = chars[i]; //左
            char bChar = chars[j]; //右
            //判断回文
            if (aChar != bChar) {
                result = false;
                break;
            }
            i++;
            j--;
        }
        return result;
    }
}
