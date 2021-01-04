package com.niux.spring.stringmatch;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("sss", "s*"));
    }

    /**
     * 如果 p_jp
     * j
     * ​
     *   是小写字母，那么 s_is
     * i
     * ​
     *   必须也为相同的小写字母，状态转移方程为：
     *
     * dp[i][j] = (s_i~与~p_j~相同) \wedge dp[i-1][j-1]
     * dp[i][j]=(s
     * i
     * ​
     *   与 p
     * j
     * ​
     *   相同)∧dp[i−1][j−1]
     *
     * 其中 \wedge∧ 表示逻辑与运算。也就是说，dp[i][j]dp[i][j] 为真，当且仅当 dp[i-1][j-1]dp[i−1][j−1] 为真，并且 s_is
     * i
     * ​
     *   与 p_jp
     * j
     * ​
     *   相同。
     *
     * 如果 p_jp
     * j
     * ​
     *   是问号，那么对 s_is
     * i
     * ​
     *   没有任何要求，状态转移方程为：
     *
     * dp[i][j] = dp[i-1][j-1]
     * dp[i][j]=dp[i−1][j−1]
     *
     * 如果 p_jp
     * j
     * ​
     *   是星号，那么同样对 s_is
     * i
     * ​
     *   没有任何要求，但是星号可以匹配零或任意多个小写字母，因此状态转移方程分为两种情况，即使用或不使用这个星号：
     *
     * dp[i][j] = dp[i][j-1] \vee dp[i-1][j]
     * dp[i][j]=dp[i][j−1]∨dp[i−1][j]
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/wildcard-matching/solution/tong-pei-fu-pi-pei-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        /*
        我们用 dp[i][j] 表示字符串 s的前 i 个字符和模式 p 的前 j 个字符是否能匹配。
         */
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[m][n];
    }
}

//作者：LeetCode-Solution
//        链接：https://leetcode-cn.com/problems/wildcard-matching/solution/tong-pei-fu-pi-pei-by-leetcode-solution/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。