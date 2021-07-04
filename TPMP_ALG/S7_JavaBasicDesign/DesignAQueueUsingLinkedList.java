package S7_JavaBasicDesign;

public class DesignAQueueUsingLinkedList<E> {
    // fields
    private ListNode<E> head, tail;
    int length;

    // methods
    public DesignAQueueUsingLinkedList() {
        head = tail = null;
        length = 0;
    }

    public void offer(E val) {  // 尾进
        if (tail == null) { // 从0到1
            tail = new ListNode<E>(val);
            head = tail;
        } else {
            ListNode<E> newTail = new ListNode<E>(val);
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
        length++;
    }

    public E poll() {    // 头出
        if (head == null) return null;
        ListNode<E> count = head;
        if (head == tail) {  // 从1到0
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            count.next = null;
        }
        length--;
        return count.val;
    }

    public E peek() {
        return head == null ? null : head.val;
    }
}
