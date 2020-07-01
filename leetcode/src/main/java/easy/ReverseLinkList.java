package easy;

import org.junit.Test;

/**
 * LeetCode:206
 * 反转一个单链表。
 * <p>
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseLinkList {
    @Test
    public void test(){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = null;
        print(a);

        print(reverseList2(a));
    }
    /**
     *  方法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode newTail = null; //建一个翻转后节点的尾巴
        while (head!=null){ //从老链表头开始向后遍历
            ListNode record = head.next; //记录下来此时的下一个节点
            head.next = newTail; //将当前节点的后置指针指向新的尾节点
            newTail = head; //尾节点发生改变,变为当前节点
            head = record; //当前节点向后移动,继续遍历
        }
        return newTail;
    }

    /**
     * 递归法
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next ==null)return head; //递归结束条件
        ListNode temp = reverseList2(head.next); //直接去找尾结点,最终返回的就是头结点
        head.next.next = head; // a->b->c->null 这步把 a->b翻转为b->a ####主要目的前后翻转 a->b->c->d->null => a->b->c->d->c->d.....
        head.next = null;  //这步把a的后置->null 也就是b->a->null ####主要目的前链断链  a->b->c->d->c->d..... => a->b->c 断开 d->c
        return temp;
    }

    public void print (ListNode node){
        while (node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}