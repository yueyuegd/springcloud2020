package sort;

import java.util.Arrays;

/**
 * 插入排序：？？？
 * 遍历数组，从第二个元素开始，依次与前面元素比较，如果大于的话移动比较的元素，找到插入的位置然后赋值
 */
public class InsertionSort {

    public static int[] InsertionSort(int[] array, int n) {
        if (n <= 0 || n == 1) {
            return array;
        }
        for (int i = 1; i < n; i++) {
            int value = array[i];
            int j = i - 1;
            for (;j >= 0; j--) {
                if (array[j] > value) {
                    //后移
                    array[j+1] = array[j];
                }
            }
            //找到位置赋值
            array[j+1] = value;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {4,5,6,1,3,2};
        System.out.println("排序前：" + Arrays.toString(array));
        array = InsertionSort(array, array.length);
        System.out.println("排序后：" + Arrays.toString(array));

    }
}
