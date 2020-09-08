package linkedlist;

/**
 * 链表基本操作：
 * 1.单链表反转
 * 2.链表中环的检测
 * 3.两个有序链表的合并
 * 4.删除链表倒数第n个节点
 * 5.求链表的中间节点
 */
public class LinkedListAlgo {

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
        public int getData() {
            return this.data;
        }
    }

    /**
     * 反转单链表：
     * @param list
     * @return
     */
    public static Node reverse(Node list) {
        Node curr = list, pre = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public static void printAll(Node list) {
        Node pre = list;
        while (pre != null) {
            System.out.println(pre.data + " ");
            pre = pre.next;
        }
        System.out.println();
    }

    /**
     * 检测是否是环形链表
     * @param list
     * @return
     */
    public static boolean checkCircle(Node list) {
        if (list == null) {
            return false;
        }
        Node fast = list.next;
        Node slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast == slow) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
