package queue;

/**
 * 基于链表实现队列
 */
public class QueueBasedOnLinkedList {

    static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }
    }

    //队列的头尾节点
    private Node head = null;
    private Node tail = null;

    /**
     * 入队某个元素
     * @param item
     */
    public void enqueue(String item) {
        Node newNode = new Node(item, null);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
    }

    /**
     * 出队：队头元素
     * @return
     */
    public String dequeue() {
        if (head == null) {
            //队空
            return null;
        }
        String value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }



}
