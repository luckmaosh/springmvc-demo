package com.niux.spring.log.test2;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        Solution solution = new Solution();
        System.out.println(solution.isAnagram(s, t));
    }

    public boolean isAnagram(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return String.valueOf(sChars).equals(String.valueOf(tChars));


    }
}