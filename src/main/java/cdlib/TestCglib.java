package cdlib;

import net.sf.cglib.proxy.Enhancer;

public class TestCglib {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(HelloWorld.class);

        enhancer.setCallback(new CglibProxy());    //回调函数

        HelloWorld helloWorld = (HelloWorld) enhancer.create();

        helloWorld.say();

    }

}