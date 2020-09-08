package stack;

/**
 * 用数组实现的栈：顺序栈
 */
public class ArrayStack {

    //元素
    private String[] items;
    //元素个数
    private int count;
    //数组大小：容量
    private int n;

    /**
     * 构造方法：初始化一个n容量的栈
     * @param n
     */
    public ArrayStack(int n) {
        this.items = new String[n];
        this.count = 0;
        this.n = n;
    }

    /**
     * 入栈操作：从索引0开始入栈
     * @param item 要入栈的元素
     * @return
     */
    public boolean push(String item) {
        //如果栈已经满了话不允许入栈
        if (count == n) {
            return false;
        }
        items[count] = item;
        count++;
        return true;
    }

    /**
     * 出栈操作，将最后元素出栈
     * @return
     */
    public String pop() {
        //如果栈空的话不用出栈
        if (count == 0) {
            return null;
        }
        String item = items[count - 1];
        count--;
        return item;
    }


}
