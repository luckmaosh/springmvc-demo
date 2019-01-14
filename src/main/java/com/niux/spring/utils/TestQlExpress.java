package com.niux.spring.utils;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

public class TestQlExpress {

    public static void main(String[] args) throws Exception {

        //ExpressRunner runner = new ExpressRunner();
        //DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        //context.put("a", 1);
        //context.put("b", 2);
        //context.put("c", 3);
        //String express = "a+b*c";
        //Object r = runner.execute(express, context, null, true, false);
        //System.out.println(r);

        TestQlExpress testQlExpress = new TestQlExpress();
        testQlExpress.test1();

    }

    private void test1() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();

        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);

        String exp = "如果  (语文+数学+英语>270) 则 {return 1;} 否则 {return 0;}";
        Object execute = runner.execute(exp, context, null, false, false, null);

        System.out.println(execute);

    }
}
