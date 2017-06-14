package com.niux.spring.utils.threads;

/**
 * Created by shaohui.mao
 * on 16/4/21.上午9:20
 */
public class Test2 {

    private int i = 0 ;

    private void increase(){
        i++;
    }


    public static void main(String[] args) {


        Test2 test2 = new Test2();

        for (int i = 0 ; i < 1000000 ; i++){
            test2.increase();
        }

        System.out.println(test2.i);
    }
}
