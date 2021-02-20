package com.niux.spring.algorithm.water;

public class WaterPool {


    public static void main(String[] args) {
        int[] p = new int[]{5, 2, 2,3,4};
        System.out.println(WaterPool.poolVolumn(p));
        System.out.println(WaterPool.water3(p));
    }

    public static int water3(int[] arr) {
        int water = 0;
        if (arr == null || arr.length <= 1)
            return 0;
        int largestLeft = 0, largestRight = 0;
        //分别保存左右两边最大值
        int left = 0, right = arr.length - 1;
        while (left < right) {
            largestLeft = Math.max(arr[left], largestLeft);
            largestRight = Math.max(arr[right], largestRight);
            //获得左右两边最大值
            if (largestLeft > largestRight) {
                //如果左边最大值大于右边则右指针向移动
                water += largestRight - arr[right--];
            } else {
                //否则左指针向右移动
                water += largestLeft - arr[left++];
            }
        }

        return water;
    }

    //自底向上的备忘录
    public static int poolVolumn(int[] arr) {

        if (arr == null || arr.length < 3) {
            return 0;
        }

        int len = arr.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        int max = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }

            leftMax[i] = max;
        }

        max = 0;
        for (int j = len - 1; j >= 0; j--) {
            if (arr[j] > max) {
                max = arr[j];
            }
            rightMax[j] = max;
        }

        int v = 0;
        for (int k = 0; k < len; k++) {
            v += Math.max(0, Math.min(leftMax[k], rightMax[k]) - arr[k]);
        }

        return v;
    }


}
