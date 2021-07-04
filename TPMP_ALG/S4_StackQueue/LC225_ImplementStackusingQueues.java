package S4_StackQueue;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ImplementStackusingQueues
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 225. Implement Stack using Queues
 */
public class LC225_ImplementStackusingQueues {
    /**
     * Implement the following operations of a stack using queues.
     *
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * empty() -- Return whether the stack is empty.
     * Example:
     *
     * MyStack stack = new MyStack();
     *
     * stack.push(1);
     * stack.push(2);
     * stack.top();   // returns 2
     * stack.pop();   // returns 2
     * stack.empty(); // returns false
     * Notes:
     *
     * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size,
     * and is empty operations are valid.
     * Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or
     * deque (double-ended queue), as long as you use only standard operations of a queue.
     * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty
     * stack).
     */
    /** Initialize your data structure here. */

    private Queue<Integer> queue;
    private int size;

    public LC225_ImplementStackusingQueues() {
        queue = new LinkedList<>();
        size = 0;
    }

    /** Push element x onto stack. */
    // time = O(n), space = O(1);
    public void push(int x) {
        queue.offer(x);
        size++;
        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    // time = O(1), space = O(1)
    public int pop() {
        size--;
        return queue.poll();
    }

    /** Get the top element. */
    // time = O(1), space = O(1)
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    // time = O(1), space = O(1)
    public boolean empty() {
        return queue.isEmpty();
    }
}
