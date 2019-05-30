package easy;

import org.junit.Test;

import java.util.Arrays;

/**
 * LeetCode:242 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     示例 1:
         输入: s = "anagram", t = "nagaram"
         输出: true
     示例 2:
         输入: s = "rat", t = "car"
         输出: false
 说明:
 你可以假设字符串只包含小写字母。
 进阶:
 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

 */
public class IsAnagram {
    @Test
    public void test(){
    }
    public boolean isAnagram(String s, String t) {
        char[] arrs = s.toCharArray();
        char[] arrt = t.toCharArray();
        Arrays.sort(arrs);
        Arrays.sort(arrt);
        return new String(arrs).equals(new String(arrt));
    }
    /*
        3ms
     */
    public boolean isAnagram2(String s, String t) {
        int[] m = new int[26];
        for(char i:s.toCharArray()) {
            int x = (int)i - (int)'a';
            m[x]++;
        }

        for(char i: t.toCharArray()) {
            int y = (int)i - (int)'a';
            m[y]--;
        }

        for(int p: m) {
            if(p!=0) {
                return  false;
            }
        }
        return true;
    }
}
