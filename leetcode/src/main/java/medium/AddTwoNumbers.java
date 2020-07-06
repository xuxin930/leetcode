package medium;

import org.junit.Test;

/**
 * LeetCode:2
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 示例：

 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807
 */
public class AddTwoNumbers {
    @Test
    public void test() {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        ListNode x = new ListNode(5);
        ListNode y = new ListNode(6);
        ListNode z = new ListNode(4);
        x.next = y;
        y.next = z;

        addTwoNumbers(a, x);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0); //头是空节点
        ListNode  c = result; //c向后遍历, result保留返回结果
        int carry = 0; //进位
        while (l1 != null || l2 != null) {
            //链表不够长的补0
            int aVal = (l1 == null ? 0 : l1.val);
            int bVal = (l2 == null ? 0 : l2.val);
            int sum = aVal + bVal + carry;
            //处理进位
            carry = sum / 10;
            c.next = new ListNode(sum % 10);
            c = c.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

        }
        //最后一位是进位
        if (carry > 0) {
            c.next = new ListNode(1);
        }
        return result.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}