package com.niux.spring;

class MultipleStringSolution {

    public static void main(String[] args) {
        String multiply = new MultipleStringSolution().multiply("28", "3");
        System.out.println(multiply);
    }

    public String multiply(String num1, String num2) {
        if (num1.length() == 0 || num2.length() == 0) {
            return "";
        }

        if (num1.startsWith("0") || num2.startsWith("0")) {
            return "0";
        }

        int m = num1.length(), n = num2.length();
        int[] ans = new int[m + n ];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j)-'0';
                ans[i + j + 1] += x * y;
            }
        }
        //进位
        int k = m + n - 1;
        while (k > 0) {
            ans[k - 1] += ans[k] / 10;
            ans[k] = ans[k] % 10;
            k--;
        }

        int index = ans[0] == 0 ? 1 : 0;
        StringBuilder sb = new StringBuilder();
        for (int h = index ; h< m + n ; h++){
            sb.append(ans[h]);
        }

        return sb.toString();
    }
}

