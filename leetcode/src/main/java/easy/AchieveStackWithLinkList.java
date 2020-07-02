package easy;

import java.util.LinkedList;

public class AchieveStackWithLinkList {

}
/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
class MyStack {
    private LinkedList<Integer> list;
    /** Initialize your data structure here. */
    public MyStack() {
        list = new LinkedList<Integer>();
    }
    /** Push element x onto stack. */
    public void push(int x) {
        list.push(x);
    }
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return list.pollFirst();
    }
    /** Get the top element. */
    public int top() {
        return list.peekFirst();
    }
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return list.isEmpty();
    }
}