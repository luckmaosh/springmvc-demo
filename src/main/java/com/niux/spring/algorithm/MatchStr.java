package com.niux.spring.algorithm;

/**
 * 通配符匹配
 */
public class MatchStr {

    public static void main(String[] args) {
        System.out.println(new MatchStr().isMatch("abc", "*"));
    }

    /**
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * <p>
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/wildcard-matching
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
//们用 dp[i][j]dp[i][j] 表示字符串 ss 的前 ii 个字符和模式 pp 的前 jj 个字符是否能匹配。在进行状态转移时，我们可以考虑模式 pp 的第 jj 个字符 p_jp
//j
//​
// ，与之对应的是字符串 ss 中的第 ii 个字符 s_is
//i
//​
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        //* 在p的前缀时
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        //p只有三种情况，* ？和字母 ， 所以使用动态规划思想

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if ('?' == (p.charAt(j - 1))) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if ('*' == (p.charAt(j - 1))) {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else {
                    //a-z
                    dp[i][j] = p.charAt(j - 1) == s.charAt(i - 1) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];


    }
}
