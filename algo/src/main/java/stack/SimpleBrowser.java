package stack;

/**
 * 使用前后栈实现浏览器的前进和后退
 */
public class SimpleBrowser {

    public static void main(String[] args) {
        /*LinkedListBasedStack linkedListBasedStack = new LinkedListBasedStack();
        linkedListBasedStack.push("A");
        linkedListBasedStack.push("B");
        linkedListBasedStack.push("C");
        linkedListBasedStack.pop();
        linkedListBasedStack.push("D");
        linkedListBasedStack.push("E");*/
        SimpleBrowser simpleBrowser = new SimpleBrowser();
        //打开A页面
        simpleBrowser.open("A");
        //在A页面上又打开B页面
        simpleBrowser.open("B");
        //在B页面上又打开了C页面
        simpleBrowser.open("C");
        //回退到B页面
        simpleBrowser.goBack();
        //又回退到A页面
        simpleBrowser.goBack();
        //前进到B页面，当前页面就是B页面
        simpleBrowser.goForward();
        simpleBrowser.checkCurrentPage();

    }

    private String currentPage;
    private LinkedListBasedStack backStack;
    private LinkedListBasedStack forwardStack;

    public SimpleBrowser() {
        this.backStack = new LinkedListBasedStack();
        this.forwardStack = new LinkedListBasedStack();
    }

    public void open(String url) {
        if (this.currentPage != null) {
            this.backStack.push(url);
            this.forwardStack.clear();
        }
        showUrl(url, "Open");
    }

    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page:" + url);
    }

    public boolean canGoBack() {
        return this.backStack.size > 0;
    }

    public boolean canGoForward() {
        return this.forwardStack.size > 0;
    }

    public String goBack() {
        if (this.canGoBack()) {
            this.forwardStack.push(this.currentPage);
            String backUrl = this.backStack.pop();
            showUrl(backUrl, "Back");
            return backUrl;
        }
        System.out.println("* Cannot go back, no pages behind.");
        return null;
    }

    public String goForward() {
        if (this.canGoForward()) {
            this.backStack.push(this.currentPage);
            String forwardUrl = forwardStack.pop();
            showUrl(forwardUrl, "Forward");
            return forwardUrl;
        }
        System.out.println("** Cannot go forward, no pages ahead.");
        return null;
    }
    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }

    public static class Node {
        private String data;
        private Node next;

        public Node(String data) {
            this(data, null);
        }

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static class LinkedListBasedStack{
        private int size;
        //栈顶元素
        private Node top;

        static Node createNode(String data, Node next) {
            return new Node(data, next);
        }
        public void clear() {
            this.top = null;
            this.size = 0;
        }
        public void push(String data) {
            Node node = createNode(data, this.top);
            this.top = node;
            this.size++;
        }
        public String pop() {
            Node node = this.top;
            if (node == null) {
                System.out.println("The Stack is Empty");
                return null;
            }
            this.top = node.next;
            if (this.size > 0) {
                this.size--;
            }
            return node.data;
        }
        public String getTopData() {
            if (this.top == null) {
                return null;
            }
            Node node = this.top;
            return node.data;
        }

        public int size() {
            return this.size;
        }

        public void print() {
            System.out.println("Print stack:");
            Node currentNode = this.top;
            while (currentNode != null) {
                String data = currentNode.getData();
                System.out.print(data + "\t");
                currentNode = currentNode.next;
            }
            System.out.println();
        }


    }
}
