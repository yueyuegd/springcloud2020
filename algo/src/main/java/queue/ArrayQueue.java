package queue;

/**
 * 使用数组实现的队列：顺序队列
 */
public class ArrayQueue {

    //元素的数组
    private String[] items;
    //数组大小
    private int n;
    //队头和队尾索引
    private int head = 0;
    private int tail = 0;

    //申请一个capacity大小的数组
    public ArrayQueue(int capacity) {
        items = new String[capacity];
        this.n = capacity;
    }

    //入队
    private boolean enqueue(String item) {
        //如果队尾索引等于n的话表示队列已满不能继续入队了
        if (this.tail == n) {
            return false;
        }
        items[tail] = item;
        ++this.tail;
        return true;
    }

    //出队
    private String dequeue() {
        //如果队头索引为0表示队列为空不能出队
        if (this.head == 0) {
            return null;
        }
        String item = items[this.head];
        ++this.head;
        /*String[] newItems = new String[n];
        System.arraycopy(this.items, 1, newItems, 0, n - 1);
        this.items = newItems;*/
        return item;
    }
}
