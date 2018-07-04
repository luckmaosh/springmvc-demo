package com.niux.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author 矛戈 on 2017/10/10.
 */
public class TestList implements Runnable {

    /**
     * 提交任务的线程，无需拿到返回值
     */
    private static ThreadPoolExecutor threadPoolExecutor = null;


  static {

        threadPoolExecutor = new ThreadPoolExecutor(4, 16, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(1024 * 32), new

            RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                }
            });
    }


    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private List<ImageData> list;

    public TestList(List<ImageData> list) {
        this.list = list;
    }

    public List<ImageData> getList() {
        return list;
    }

    public void setList(List<ImageData> list) {
        this.list = list;
    }

    @Override
    public void run() {

        if (CollectionUtils.isNotEmpty(list)) {
            System.out.println("list is not empty");

            //list= null;
        }

    }



    public void test(){
        Pattern p=Pattern.compile("([a-z]+)(\\d+)");
        Matcher m=p.matcher("aaa222a3bb");
        m.find();   //匹配aaa2223
        m.groupCount();   //返回2,因为有2组

    }
    public static void main(String[] args) {
        TestList testList1 = new TestList(null);
        testList1.test();

        Pattern p=Pattern.compile("\\d+");
        Matcher m1=p.matcher("我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com");
        int i1 = m1.groupCount();
        while(m1.find()) {
            System.out.println(m1.group());
        }




        Pattern pattern = Pattern.compile("\\d+");
        Matcher m = pattern.matcher("ab22cddd");
        boolean matches = m.matches();

        boolean b = m.find();//匹配2223
        int start = m.start();//返回3
        int end = m.end();//返回7,返回的是2223后的索引号
        String group1 = m.group();//返回2223

        String group = m.group();

        for (int i = 0 ; i < 10000000;i ++){
            List<ImageData> list = new ArrayList<ImageData>();
            list.add(new ImageData("xxx" + i));
            list.add(new ImageData("yyy" + i));
            list.add(new ImageData("zzz" + i));
            TestList testList = new TestList(list);

            System.out.println("------>"+ i);

            threadPoolExecutor.submit(testList);
        }

    }

    static class ImageData{

        private String name ;

        public ImageData(String name) {
            this.name = name;
        }
    }
}
