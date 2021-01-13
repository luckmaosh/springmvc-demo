package com.niux.spring.algorithm;

public class StrStr {


    /**
     * 算法
     * <p>
     * 移动 pn 指针，直到 pn 所指向位置的字符与 needle 字符串第一个字符相等。
     * <p>
     * 通过 pn，pL，curr_len 计算匹配长度。
     * <p>
     * 如果完全匹配（即 curr_len == L），返回匹配子串的起始坐标（即 pn - L）。
     * <p>
     * 如果不完全匹配，回溯。使 pn = pn - curr_len + 1， pL = 0， curr_len = 0。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * needle in a haystack / 草堆寻针 是个英文俗语, 相当于中文的 "大海捞针"
     *
     * @param haystack 草垛
     * @param needle   针
     * @return
     */
    public int strStr(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L == 0) return 0;

        int pn = 0;
        while (pn < n - L + 1) {
            // find the position of the first needle character
            // in the haystack string
            while (pn < n - L + 1 && haystack.charAt(pn) != needle.charAt(0)) ++pn;

            // compute the max match string
            int currLen = 0, pL = 0;
            while (pL < L && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
                ++pn;
                ++pL;
                ++currLen;
            }

            // if the whole needle string is found,
            // return its start position
            if (currLen == L) return pn - L;

            // otherwise, backtrack
            pn = pn - currLen + 1;
        }
        return -1;
    }
}

//作者：LeetCode
//        链接：https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。