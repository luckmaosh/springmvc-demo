package com.niux.spring.algorithm.string;

class numDistinct {
    //方法1 循环遍历。 耗时 30s
    //方法2 动态规划 耗时 1ms
    public static void main(String[] args) {
        numDistinct numDistinct = new numDistinct();
        long l = System.currentTimeMillis();
        System.out.println(numDistinct.numDistinct("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc",
                "bcddceeeebecbc"));

        System.out.println("cost:" + (System.currentTimeMillis() - l));
    }

    private int count = 0;

    public int numDistinct(String s, String t) {

        if (s == null || t == null || t.length() > s.length()) {
            return 0;
        }
        match(s, t, 0, 0);

        return count;
    }

    public void match(String s, String t, int si, int ti) {
        for (int i = si; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(ti)) {
                if (ti == t.length() - 1) {
                    count++;
                } else {
                    // i +1
                    match(s, t, i + 1, ti + 1);
                }
            }
        }


    }


}