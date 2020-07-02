package easy;

import org.junit.Test;

import java.util.Stack;

/**
 * LeetCode:20
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * <p>
 * 输入: "()"
 * 输出: true
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * <p>
 * 输入: "(]"
 * 输出: false
 * <p>
 * 输入: "([)]"
 * 输出: false
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
public class ValidParenthesis {
    private int s = "(".charAt(0) + ")".charAt(0);
    private int m = "[".charAt(0) + "]".charAt(0);
    private int l = "{".charAt(0) + "}".charAt(0);

    @Test
    public void test() {
        System.out.println(isValid("([]()}"));
    }

    public boolean isValid(String str) {
        if (str.length() % 2 != 0) {
            return false;
        }
        return "".equals(del(str, true));
    }

    /**
     * 我的SB思路,挨着的括号一对一对的删,删到最后没有了就是呗
     * 有点SB
     * 执行用时：243 ms, 在所有 Java 提交中击败了5.22%的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了5.48%的用户
     *
     * @param str
     * @param ifContinue
     * @return
     */
    public String del(String str, boolean ifContinue) {
        if ("".equals(str) || !ifContinue) {
            return str;
        }
        boolean flag = false;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            int temp = chars[i] + chars[i + 1];
            if (temp == s || temp == m || temp == l) {
                if (!flag) {
                    flag = true;
                }
                chars[i] = 0;
                chars[i + 1] = 0;
            }
        }
        return del(new String(chars).replaceAll(String.valueOf((char) 0), ""), flag);
    }

    /**
     *  用栈实现
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else {
                if (!stack.empty() && c == stack.peek()) stack.pop();
                else return false;
            }
        }
        return stack.empty();
    }
}
