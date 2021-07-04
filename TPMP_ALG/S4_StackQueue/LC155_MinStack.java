package S4_StackQueue;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MinStack
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 155. Min Stack
 */
public class LC155_MinStack {
    /**
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     *
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     *
     *
     * Example 1:
     *
     * Input
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     *
     * Output
     * [null,null,null,null,-3,null,0,-2]
     *
     * Explanation
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin(); // return -3
     * minStack.pop();
     * minStack.top();    // return 0
     * minStack.getMin(); // return -2
     *
     *
     * Constraints:
     *
     * Methods pop, top and getMin operations will always be called on non-empty stacks.
     */

    /** initialize your data structure here. */
    // time = O(1), space = O(n)
    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public LC155_MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        mainStack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (mainStack.isEmpty()) return;
        int top = mainStack.pop();
        if (top == minStack.peek()) minStack.pop();
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
