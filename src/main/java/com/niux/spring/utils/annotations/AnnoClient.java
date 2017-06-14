package com.niux.spring.utils.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by shaohui.mao
 * on 16/4/25.上午9:54
 */
public class AnnoClient {


    @TestAnno
    public void method() {

    }


    public static void main(String[] args) {

        AnnoClient annoClient = new AnnoClient();
        Annotation[] annotations = annoClient.getClass().getAnnotations();
        System.out.println(annotations);

        annoClient.method();


        Method[] methods = AnnoClient.class.getMethods();
        for (Method m : methods) {

            TestAnno annotation = m.getAnnotation(TestAnno.class);
            if (annotation != null) {

                System.out.println("yes");
            }
        }


    }
}
