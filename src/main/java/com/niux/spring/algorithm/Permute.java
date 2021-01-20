package com.niux.spring.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 全排列 , 不包含重复的数字
 */
class Permute {

    public static void main(String[] args) {
        List<List<Integer>> permute = new Permute().permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    /**
     * @param n
     * @param output
     * @param res
     * @param first
     */
    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }




}

//作者：LeetCode-Solution1
//        链接：https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。