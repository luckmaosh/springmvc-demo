package com.niux.spring.algorithm.divide;

public class Solution {
    public static void main(String[] args) {
        int divide = new Solution().divide(-2147483648, 2);
        System.out.println(divide);
    }

    public int divide(int dividend, int divisor) {

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }

        long d = Math.abs((long) dividend), b =  Math.abs((long)divisor), res = 0;

        if (b == 1) {
            return (int) (((dividend < 0) ^ (divisor < 0)) ? 0L - d : d);
        }

        while (d >= b) {
            long beizhu = 1, temp = b;
            while (d >= (temp << 1)) {
                temp <<= 1;
                beizhu <<= 1;
            }

            d = d - temp;
            res += beizhu;

        }
        return (int) (((dividend < 0) ^ (divisor < 0)) ? 0L - res : res);
    }
}
