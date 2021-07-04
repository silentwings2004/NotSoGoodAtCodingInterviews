package S4_StackQueue;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ImplementQueueusingStacks
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 232. Implement Queue using Stacks
 */
public class LC232_ImplementQueueusingStacks {
    /**
     * Implement the following operations of a queue using stacks.
     *
     * push(x) -- Push element x to the back of queue.
     * pop() -- Removes the element from in front of queue.
     * peek() -- Get the front element.
     * empty() -- Return whether the queue is empty.
     * Example:
     *
     * MyQueue queue = new MyQueue();
     *
     * queue.push(1);
     * queue.push(2);
     * queue.peek();  // returns 1
     * queue.pop();   // returns 1
     * queue.empty(); // returns false
     * Notes:
     *
     * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and
     * is empty operations are valid.
     * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or
     * deque (double-ended queue), as long as you use only standard operations of a stack.
     * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty
     * queue).
     */
    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;
    /** Initialize your data structure here. */
    public LC232_ImplementQueueusingStacks() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    /** Push element x to the back of queue. */
    // time = O(1), space = O(n)
    public void push(int x) {
        stackIn.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    // time = O(n), space = O(n)
    public int pop() {
        move();
        return stackOut.pop();
    }

    /** Get the front element. */
    // time = O(n), space = O(n)
    public int peek() {
        move();
        return stackOut.peek();
    }

    /** Returns whether the queue is empty. */
    // time = O(1), space = O(1)
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    private void move() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) stackOut.push(stackIn.pop());
        }
    }
}
