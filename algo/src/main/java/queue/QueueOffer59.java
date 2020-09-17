package queue;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 剑指offer 第59题
 */
public class QueueOffer59 {

    public static void main(String[] args) {
        QueueOffer59 queueOffer59 = new QueueOffer59();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] array = queueOffer59.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(array));
        nums = new int[]{1, -1};
        k = 1;
        array = queueOffer59.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(array));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        /*int n = 0;
        for (int i = 0;i < nums.length; i++) {
            int j = i;
            if (j < (k + i) && (k + i) <= nums.length) {
                n++;
            }
            j++;
        }*/
        int n = nums.length - k + 1;
        int[] array = new int[n];
        for (int i = 0;i < nums.length; i++) {
            int max = nums[i];
            for (int j = i; j < (k + i) && (k + i) <= nums.length; j++) {
                if (nums[j] >= max) {
                    max = nums[j];
                }
            }
            if (i < n) {
                array[i] = max;
            }

        }
        return array;

    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length;
        if (len == 0){
            return new int[0];
        }
        //定义结果数组
        int[] res = new int[len - k + 1];
        //maxInd记录每次最大值的下标，max记录最大值
        int maxInd = -1, max = Integer.MIN_VALUE;

        for (int i = 0; i < len - k + 1; i++) {
            //判断最大值下标是否在滑动窗口的范围内
            if (maxInd >= i){
                //存在就只需要比较最后面的值是否大于上一个窗口最大值
                if (nums[i + k - 1] > max){
                    max = nums[i + k - 1];
                    //更新最大值下标
                    maxInd = i + k - 1;
                }
            }
            //如果不在就重新寻找当前窗口最大值
            else {
                max = nums[i];
                for (int j = i; j < i + k; j++) {
                    if (max < nums[j]) {
                        max = nums[j];
                        maxInd = j;
                    }
                }
            }
            res[i] = max;
        }
        return res;
    }
}
