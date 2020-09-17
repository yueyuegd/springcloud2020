package queue;

/**
 * 使用数组实现的循环队列
 */
public class CircularQueue {

    //循环数组,n为数组大小
    private String[] items;
    private int n = 0;
    //队头和队尾索引
    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 入队操作
     * @param item
     */
    public boolean enqueue(String item) {
        //队满不能入队
        if ((this.tail + 1) % n == this.head) {
            return false;
        }
        items[this.tail] = item;
        this.tail = (this.tail + 1) % n;
        return true;
    }

    /**
     * 出队操作
     * @return
     */
    public String dequeue() {
        //队列为空不能出队
        if (this.tail == this.head) {
            return null;
        }
        String item = items[head];
        this.head = (this.head + 1) % n;
        return item;
    }


}
