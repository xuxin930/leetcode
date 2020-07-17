package datastructure;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 字典数 Trie 也叫前缀树
 * 可用字符串检索,词频统计,热词搜索
 * 特征:根节点不包含字符,除根节点外的每一个节点都只包含一个字符;
 * 从根节点到某一节点,路径上的字符连接起来为该节点对应字符串;
 * 每个节点的所有子节点包含的字符都不相同;
 */
public class Trie {
    private TrieNode root; //根

    Trie() {
        root = new TrieNode();
    }

    public static void main(String[] args)
    {
        Trie trie = new Trie();
        String[] strs = {"inn", "innt", "int", "an", "abn", "abnnns"};
        for (String s : strs) {
            trie.insert(s);
        }
        System.out.println(trie.hasString("innw"));
        System.out.println(trie.countPrefix("ab"));
    }

    /**
     * 树中插入字符串
     *
     * @param str
     */
    public void insert(String str) {
        if (StringUtils.isEmpty(str)) return;
        char[] chars = str.toCharArray();
        TrieNode node = root; //根
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a'; //字母坐标,方便暂定都是小写
            if (node.childs[index] == null) { //创建新节点
                node.hasChild = true;
                node.childs[index] = new TrieNode();
                node.childs[index].val = chars[i];
            } else {
                node.childs[index].num++;
            }
            node = node.childs[index];
        }
        node.isString = true;
    }

    /**
     * 计算公共前缀数量
     *
     * @param prefix
     * @return
     */
    public int countPrefix(String prefix) {
        if (StringUtils.isEmpty(prefix)) return -1;
        TrieNode node = root;
        char[] chars = prefix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.childs[index] == null) {
                return 0;
            } else {
                node = node.childs[index];
            }
        }
        return node.num;
    }

    /**
     * 判断是否存储某个字符串
     *
     * @param str
     * @return
     */
    public boolean hasString(String str) {
        if (StringUtils.isEmpty(str)) return false;
        TrieNode node = root;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.childs[index] == null) {
                return false;
            } else {
                node = node.childs[index];
            }
        }
        return node.isString;
    }
}

/**
 * 字典数节点
 */

class TrieNode {
    int num; //多少个词通过当前节点,即从根节点至该节点的公共串出现次数
    TrieNode[] childs; // 子节点
    boolean isString; //是不是一个词
    Character val;
    boolean hasChild;

    TrieNode() {
        num = 1;
        childs = new TrieNode[26];
    }
}