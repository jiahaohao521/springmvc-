package com.jiahao.Interceptor.four;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 对代理类进行封装
 */
class MyProxy {

    public static Object getProxy(Object target){

        ClassLoader loader = MyProxy.class.getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler handler = new MyHandler(target);
        Object o = Proxy.newProxyInstance(loader, interfaces, handler);
        return o;

    }
}

class MyHandler implements InvocationHandler {

    //创建目标对象，是其与其他对类进行关联
    private Object target;

    public MyHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println(method.getName() + "方法开始");
        //有目标对象target调用方法
        Object r = method.invoke(target, objects);
        System.out.println(method.getName() + "方法结束");
        return r;
    }
}

public class TestIcalc {

    @Test
    public void test() {

        ICalc calc = new CalcImpl();
        ICalc proxy = (ICalc) MyProxy.getProxy(calc);
        proxy.add(1,3);
    }



}
