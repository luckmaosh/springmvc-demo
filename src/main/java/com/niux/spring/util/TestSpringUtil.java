package com.niux.spring.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 矛戈 on 2018/3/13.
 */
public class TestSpringUtil {

    private static final Logger logger = LoggerFactory.getLogger(TestSpringUtil.class);

    private List<Object> list;

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public TestSpringUtil() {
    }

    public static void main(String[] args) {

        String input = "IP\n"
            + "人群\n"
            + "元素\n"
            + "功能\n"
            + "功能性\n"
            + "厚度\n"
            + "品牌\n"
            + "商场同款\n"
            + "图案\n"
            + "场景\n"
            + "工艺\n"
            + "新品\n"
            + "服务\n"
            + "材质\n"
            + "款式\n"
            + "版型\n"
            + "硬度\n"
            + "重量\n"
            + "钢托\n"
            + "颜色\n"
            + "风格";

        String format = "insert into `semantic_tag_label`(   gmt_create,  gmt_modified,  creator,  modifier,  "
            + "label_ident,  label_name,  description,  status,  features) \n"
            + "values(now() , now() , '自动生成', null, '%s', '%s', null, 1, null);\n";

        String format2 = "insert into `semantic_tag_label_relation`(   gmt_create,  gmt_modified,  creator,  "
            + "modifier,  parent_label_ident,  child_label_ident,  status,  features) \n"
            + "values( now(), now() , '自动生成', null, '决策属性', '%s', 1, null);\n";
        String[] split = input.split("\n");


        for (String in : split) {


        }

        for (String in : split) {
            System.out.printf(format2, in.trim());

        }

    }
}
