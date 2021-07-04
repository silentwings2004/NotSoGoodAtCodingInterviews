package S7_JavaBasicDesign;

public class DesignAStackUsingArrayWithCertainCapacity<E> {
    private E[] array; // --> ArrayList 下面可以自动作expand
    private int head;

    // [0, head)
    public DesignAStackUsingArrayWithCertainCapacity(int capacity) {
        array = new E[capacity];
        head = 0;
    }

    public boolean offer(E val) {
        if (head >= array.length) return false;
        array[head++] = val;
        return true;
    }

    public E pop() {
        if (head == 0) return null;
        return array[--head];
    }

    public E peek() {
        return head == 0 ? null : array[head - 1];
    }

    // [0, head]
    public DesignAStackUsingArrayWithCertainCapacity(int capacity) {
        array = new E[capacity];
        head = -1;  // head为空时在-1位置
    }

    public boolean offer2(E val) {
        if (head >= array.length - 1) return false;
        array[++head] = val;
        return true;
    }

    public E pop2() {
        if (head == -1) return null;
        E val = array[head];
        head--;
        return val;
    }

    public E peek2() {
        return head == -1 ? null : array[head];
    }
}
