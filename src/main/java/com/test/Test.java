package com.test;

import com.google.gson.Gson;

/**
 * Created by shaohui.msh on 2017/1/17.
 */
public class Test {


    public static void main(String[] args) {
        String s = "{\"img\":\"//img.alicdn.com/bao/uploaded/i6/TB1vEOkPXXXXXcMaXXXJV0w_XXX_052022.jpg\",\"title\":\"雪纳瑞狗粮 成犬\t 2.5kg好主人狗粮 雪纳瑞成犬天然狗粮21省包邮\",\"url\":\"http://item.taobao.com/item.htm?id=524765263666\"}";
        Gson gson = new Gson();
        String s1 = gson.toJson(s);
        System.out.println(s1);
    }
}
