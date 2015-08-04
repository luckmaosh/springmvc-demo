package com.niux.mywork.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class PriceUtil {

    public static final long LONG_COFF = 1000;

    public static final double COFF = LONG_COFF;

    public static final double EPSILON = 0.0001d;

    public static final double toDouble(Long value) {
        if (value == null) {
            return 0;
        } else {
            return toDouble(value.longValue());
        }
    }

    public static final double toDouble(long value) {
        return value / COFF;
    }

    public static final long fromDouble(Number value) {
        if (value == null) {
            return 0;
        } else {
            return fromDouble(value.doubleValue());
        }
    }

    public static final long fromDouble(double value) {
        value = Math.round(value * COFF);
        if (value > 0) {
            return (long) (value + EPSILON);
        } else {
            return (long) (value - EPSILON);
        }
    }

    public static double truncate(double money) {
        long longValue = fromDouble(money);
        longValue = ((longValue + 5) / 10) * 10;
        return toDouble(longValue);
    }


    /**
     * @param certNum    券数量
     * @param amountGift 代金券总额 ,厘为单位
     * @param scale      小数点后保留几位
     * @return 返回的以元为单位
     */
    public static List<Double> calculateMoney(int certNum, long amountGift, int scale) {
        List<Double> list = new ArrayList<Double>();
        if (amountGift < 0 || certNum < 1) {
            return list;
        }

        //代金券为0
        if (amountGift == 0) {
            for (int i = 0; i < certNum; i++) {
                list.add(0.0);
            }
            return list;
        }

        //代金券不为0
        BigDecimal amount = new BigDecimal(amountGift);
        amount = amount.divide(new BigDecimal(1000));

        while (amount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal each = amount.divide(new BigDecimal(certNum), scale, BigDecimal.ROUND_UP);
            list.add(each.doubleValue());
            amount = amount.subtract(each);
            certNum--;
        }


        //代金券已经被分挂完，剩下的糯米券只能得0
        while (certNum > 0) {
            list.add(BigDecimal.ZERO.doubleValue());
            certNum--;
        }

        System.out.println(list.size());
        return list;
    }

    public static void main(String[] args) {
//        double d = 20.51;
//        System.out.println(truncate(d));
//
//        double d1 = 20989.800;
//        double d2 = 20989.799;
//        if (PriceUtil.fromDouble(d1) == PriceUtil.fromDouble(d2)) {
//            System.out.println("y");
//        } else {
//            System.out.println("n");
//        }
//        System.out.println(truncate(d1));
//        System.out.println(truncate(d2));

        List<Double> result = calculateMoney(7, 0, 1);
        System.out.println(result);
    }


}
