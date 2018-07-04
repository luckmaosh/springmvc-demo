package com.niux.spring;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 矛戈 on 2017/12/12.
 */
public class Lambada {

    public static void main(String[] args) {
        List<TestList> list = new ArrayList<TestList>();

        list.sort((TestList t1, TestList t2) -> t1.hashCode() - t2.hashCode());

        list.sort((t1, t2) -> t1.hashCode() - t2.hashCode());

        double a = list.stream().filter(p -> p.getFirstName().startsWith("a"))
            .limit(5).distinct().mapToInt(p -> 1).average().getAsDouble();

        System.out.println(a);
    }
}
