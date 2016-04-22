package com.niux.springmvcdemo.utils.threads;

import java.util.ArrayList;
import java.util.List;

public class Lock {

    public static List<Integer> numberList = new ArrayList<Integer>();

    public static class AddToList implements Runnable {
        int startnum = 0;

        public AddToList(int startnumber) {
            startnum = startnumber;
        }

        public void run() {
            int count = 0;
            while (count < 1000000) {
                numberList.add(startnum);
                startnum += 2;
                count++;
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread t1 = new Thread(new AddToList(0));
        Thread t2 = new Thread(new AddToList(1));
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(numberList.size());
    }

}
