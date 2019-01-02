package com.niux.spring.util;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

public class QlExpressDemo {


    public static void main(String[] args) throws Exception {


        //(1)addOperator
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        runner.addOperator("join", new JoinOperator());
        Object r = runner.execute("1 join 2 join 3", context, null, false, false);
        System.out.println(r);

//        String exp1 = "if(if 1==2 then false else true) then{2+2;} else{20 + 20;}";
//        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        String exp1= "3 > 2 and 2 > 3";
        Object execute = runner.execute(exp1, context, null, false, false, null);

        System.out.println(execute);




        runner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs",
                new String[]{"double"}, null);
        runner.addFunctionOfClassMethod("转换为大写", BeanExample.class.getName(),
                "upper", new String[]{"String"}, null);

        runner.addFunctionOfServiceMethod("打印", System.out, "println", new String[]{"String"}, null);
        runner.addFunctionOfServiceMethod("contains", new BeanExample(), "anyContains",
                new Class[]{String.class, String.class}, null);

        String exp = "取绝对值(-100);转换为大写(\"hello world\");打印(\"你好吗？\")；contains(\"helloworld\",\"aeiou\")";
        Object execute1 = runner.execute(exp, context, null, false, false);


        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);


    }
}

class BeanExample {
    public static String upper(String abc) {
        return abc.toUpperCase();
    }

    public boolean anyContains(String str, String searchStr) {

        char[] s = str.toCharArray();
        for (char c : s) {
            if (searchStr.contains(c + "")) {
                return true;
            }
        }
        return false;
    }
}
