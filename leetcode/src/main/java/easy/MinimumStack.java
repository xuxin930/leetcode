package easy;

import java.util.Stack;

/**
 * LeetCode155:最小栈
 *
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
     push(x) -- 将元素 x 推入栈中。
     pop() -- 删除栈顶的元素。
     top() -- 获取栈顶元素。
     getMin() -- 检索栈中的最小元素。
 示例:
     MinStack minStack = new MinStack();
     minStack.push(-2);
     minStack.push(0);
     minStack.push(-3);
     minStack.getMin();   --> 返回 -3.
     minStack.pop();
     minStack.top();      --> 返回 0.
     minStack.getMin();   --> 返回 -2.
 */
public class MinimumStack {

    Stack<Integer> mainStack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    /** initialize your data structure here. */
    public MinimumStack() {

    }

    public void push(int x) {
        mainStack.push(x);
        if (minStack.empty() || minStack.peek()>=x){
            minStack.push(x);
        }
    }

    public void pop() {
        Integer pop = mainStack.pop();
        if (pop .equals(minStack.peek())){
            minStack.pop();
        }
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinimumStack minStack = new MinimumStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());

    }


}
