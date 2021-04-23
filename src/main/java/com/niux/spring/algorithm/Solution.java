package com.niux.spring.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> letterCombinations(String digits) {

        List<String> r = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return r;
        }

        Map<Integer, String> phone = new HashMap<>();
        phone.put(1, "");
        phone.put(2, "abc");
        phone.put(3, "def");
        phone.put(4, "ghi");
        phone.put(5, "jkl");
        phone.put(6, "mno");
        phone.put(7, "pqrs");
        phone.put(8, "tuv");
        phone.put(9, "wxyz");

        StringBuffer sb = new StringBuffer();
        backTrace(digits, sb, 0, r, phone);
//        System.out.println(r);
        return r;

    }

    private void backTrace(String digits, StringBuffer sb, int index, List<String> r, Map<Integer, String> phoneMap) {
        if (index > digits.length() - 1) {
            r.add(sb.toString());
        }
        char c = digits.charAt(index);
        String s = phoneMap.get(c - '0');
        if (s == null || s.length() == 0) {
            return;
        }
        int slen = s.length();
        for (int j = 0; j < slen; j++) {
            sb.append(s.charAt(j));
            backTrace(digits, sb, index + 1, r, phoneMap);
            //回溯
            sb.deleteCharAt(index);
        }

    }

}