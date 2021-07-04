package S7_JavaBasicDesign;

public class DesignAStackUsingLinkedList<E> {
    // fields
    private ListNode<E> head;

    // methods
    DesignAStackUsingLinkedList() {
        head = null;
    }

    public void push(E val) {
        ListNode<E> newNode = new ListNode<E>(val);
        newNode.next = head;
        head = newNode;
    }

    public E pop() {
        if (head == null) return null;
        ListNode<E> popNode = head;  // head先cache出来
        head = head.next;
        popNode.next = null;  // 被删的值后面的箭头要断开
        return popNode.val;  // 被删的值return出来
    }

    public E peek() {
        return head == null ? null : head.val;  // 注意读值可能为null，得check
    }
}
