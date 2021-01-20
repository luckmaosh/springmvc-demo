package com.niux.spring.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列 , 包含重复的数字
 */
class PermuteUnique {

    boolean[] vis;

    public static void main(String[] args) {
        List<List<Integer>> permute = new PermuteUnique().permuteUnique(new int[]{1, 1, 2, 2});
        System.out.println(permute);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int first, List<Integer> perm) {
        if (first == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            //vis[i - 1]  上一个等于false，说明已经放进去了
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, first + 1, perm);
            //把vis改成false
            vis[i] = false;
            perm.remove(first);
        }

//        作者：LeetCode-Solution1
//        链接：https://leetcode-cn.com/problems/permutations-ii/solution/quan-pai-lie-ii-by-leetcode-solution/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    }
}

//作者：LeetCode-Solution1
//        链接：https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。