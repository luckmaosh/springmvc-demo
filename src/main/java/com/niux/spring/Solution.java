package com.niux.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public static void main(String[] args) {
//        int[] nums = new int[]{0,0,0,0};
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        Solution solution = new Solution();
        System.out.println(solution.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // sum to zero

        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 1; j++) {
                if (  nums[j] == nums[i] &&  j-1 > i ) {
                    continue;
                }

                int k = nums.length - 1;

                while (nums[i] + nums[j] + nums[k] > 0 && j < k) {
                    k--;
                }

                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> arr = new ArrayList<Integer>();
                    arr.add(nums[i]);
                    arr.add(nums[j]);
                    arr.add(nums[k]);
                    res.add(arr);
                }
            }


        }

        return res;

    }
}