package com.niux.spring.algorithm.divide;

public class Divisor {

    public static void main(String[] args) {
        int divide = new Divisor().divide(8, 3);
        System.out.println(divide);
    }

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        long d = Math.abs((long) dividend), b = Math.abs((long) divisor), res = 0;
        if (1 == b) {
            return (int) (((dividend < 0) ^ (divisor < 0)) ? d * -1 : d);
        }

        while (d >= b) {
            //b是除数，beishu是倍数
            long tmp = b, beishu = 1;
            while (d >= (tmp << 1)) {
                tmp <<= 1;
                beishu <<= 1;
            }
            d -= tmp;
            res += beishu;
        }

        return (int) (((dividend < 0) ^ (divisor < 0)) ? -res : res);
    }

}
