package easy;

/**
 * LeetCode125:验证回文串
 * <p>
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 48～57号为0～9十个阿拉伯数字；65～90号为26个大写英文字母，97～122号为26个小写英文字母
 */
public class PalindromeStringVerify {
    public boolean isPalindrome(String s) {
        boolean result = true;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            char aChar = chars[i]; //左
            char bChar = chars[j]; //右
            //左侧不是字符
            if (!isNeedChar(aChar)){
                i++;
                continue;
            }
            //右侧不是满足字符
            if (!isNeedChar(bChar)){
                j--;
                continue;
            }
            //转换大小写
            if (aChar>=97){
                aChar = (char) (aChar - 32);
            }
            if (bChar>=97){
                bChar = (char) (bChar - 32);
            }
            //判断回文
            if (aChar != bChar){
                result = false;
                break;
            }
            i++;
            j--;

        }
        return result;
    }

    //判断是否是一个满足的字符
    private boolean isNeedChar(char c) {
        return (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }
}
