package com.niux.spring.algorithm.string;

class Solution {

    public static void main(String[] args) {
        Solution numDistinct = new Solution();
        long l = System.currentTimeMillis();
        System.out.println(numDistinct.numDistinct("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc",
                "bcddceeeebecbc"));

        System.out.println("cost:" + (System.currentTimeMillis() - l));

    }

    public int numDistinct(String s, String t) {
        //dp[i][j] 代表 T 前 i 字符串可以由 S 前j 字符串组成最多个数.

        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int j = 0; j < s.length() + 1; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[t.length()][s.length()];
    }
}
//
//作者：powcai
//链接：https://leetcode-cn.com/problems/distinct-subsequences/solution/dong-tai-gui-hua-by-powcai-5/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。