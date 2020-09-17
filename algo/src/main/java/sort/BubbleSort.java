package sort;

import java.util.Arrays;

/**
 * 实现冒泡排序
 */
public class BubbleSort {

    /**
     *
     * @param arrary 待排序的数据
     * @param n      待排序的数据个数
     * @return       返回排好序的数据
     */
    public static int[] sort(int[] arrary, int n) {
        if (n <= 0 || n == 1) {
            return arrary;
        }
        for (int i = 0; i < arrary.length; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arrary[j] > arrary[j + 1]) {
                    //交换位置
                    int temp = arrary[j];
                    arrary[j] = arrary[j + 1];
                    arrary[j + 1] = temp;
                    //设置flag:表示有元素交换
                    flag = true;
                }
            }
            //一次比较之后判断是否有元素交换，没有表示已经有序则可以退出循环
            if (!flag) {
                break;
            }
        }
        return arrary;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] array = {4,5,6,3,2,1};
        System.out.println("冒泡排序之前：" + Arrays.toString(array));
        array = sort(array, array.length);
        System.out.println("冒泡排序之后：" + Arrays.toString(array));
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");
    }
}
