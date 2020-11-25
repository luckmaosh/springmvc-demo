package com.niux.spring.log.test2;
//
//public class MyThread extends Thread {
//    @Override
//    public void run() {
//        super.run();
//        for (int i = 0; i < 500000; i++) {
//            if (this.interrupted()) {
//                System.out.println("should be stopped and exit");
//                break;
//            }
//            System.out.println("i=" + (i + 1));
//        }
//        System.out.println("this line is also executed. thread does not stopped");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
//    }
//}

public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.interrupted()) {
                    System.out.println("should be stopped and exit");
                    throw new InterruptedException();
                }
                System.out.println("i=" + (i + 1));
            }
            System.out.println("this line cannot be executed. cause thread throws exception");//这行语句不会被执行!!!
        } catch (InterruptedException e) {
            System.out.println("catch interrupted exception");
            e.printStackTrace();
        }
    }
}