package com.niux.spring.algorithm;

/**
 * 中位数
 */
class MedianSortedArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        MedianSortedArray solution = new MedianSortedArray();
        solution.findMedianSortedArrays(nums1, nums2);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int i = 0;
        int j = 0;
        int m = nums1.length;
        int n = nums2.length;
        int l1 = 0;
        int r1 = m;

        int l2 = 0;
        int r2 = n;


        while (true) {
            i = Math.max(0, (l1 + r1) / 2);
            j = Math.max(0, (m + n) / 2 - i);

            System.out.printf("i=" + i + " j=" + j + "\n");


            if ((i - 1 < 0 || nums2[j] >= nums1[i - 1]) &&
                    (j - 1 < 0 || nums1[i] >= nums2[j - 1])) {
                break;
            } else if (j - 1 < 0 || nums1[i] < nums2[j - 1]) {
                //i 右移
                l1 = i;
                r1 = r1;
            } else if (i - 1 < 0 || nums1[i - 1] > nums2[j]) {
                //i 左移    
                l1 = l1;
                r1 = i;
            }
        }

        System.out.printf("i=" + i);
        System.out.printf("j=" + j);
        if ((m + n) % 2 == 0) {
            return (nums1[i] + nums2[j]) / 2;
        }
        return Math.min(nums1[i], nums2[j]);

    }

}