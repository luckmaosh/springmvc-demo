package cdlib;

import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] args,

                            net.sf.cglib.proxy.MethodProxy methodProxy) throws Throwable {

        System.out.println("Before Helloworld~");

        methodProxy.invokeSuper(object, args);

        System.out.println("After Helloworld~");

        return null;

    }

}