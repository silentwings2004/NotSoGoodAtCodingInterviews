package S7_JavaBasicDesign;

public class DesignAQueueUsingArrayWithCertainCapacity<E> {
    private E[] array;
    private int head;
    private int tail;
    private int size;

    private static final int DEFAULT_CAPACITY = 10; // ==> overloading

    public DesignAQueueUsingArrayWithCertainCapacity(int capacity) {
        array = new E[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public DesignAQueueUsingArrayWithCertainCapacity() {
        this(DEFAULT_CAPACITY);   // overloading初始化的写法
    }

    public boolean offer(E val) { //TODO: Expand
        if (size == array.length) return false;
        array[tail] = val;
        tail = (tail + 1) % array.length; // tail %= array.length
        size++;
        return true;
    }

    public boolean offer2(E val) {  // 手动Expand
        E[] expArray = new E[array.length * 2];
        if (size == array.length) expArray = autoExpand(array, expArray);
        head = 0;
        tail = array.length;
        expArray[tail] = val;
        tail = (tail + 1) % expArray.length;
        size++;
        return true;
    }

    public E poll() {
        if (size == 0) return null;
        E res = array[head];
        head = (head + 1) % array.length; // 在出界的一瞬间立即拉回来，绝无可能让它出界
        size--;
        return res;
    }

    public E peek() {
        return size == 0 ? null : array[head];
    }

    private E[] autoExpand(E[] array, E[] expArray) {
        for (int i = 0; i < array.length; i++) {
            expArray[i] = array[(head + i) % array.length];
        }
        return expArray;
    }
}
