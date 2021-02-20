package com.niux.spring.algorithm;

import java.util.Arrays;

public class MainElement {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int majorityElement = nums[nums.length / 2];
        int count = 0;
        for (int num : nums) {
            if (num == majorityElement) {
                count++;
            }
        }

        if (count > nums.length / 2) {
            return majorityElement;
        }

        return -1;
    }

}
