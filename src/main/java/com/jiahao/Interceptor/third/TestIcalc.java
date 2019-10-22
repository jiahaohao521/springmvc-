package com.jiahao.Interceptor.third;

import org.junit.jupiter.api.Test;

import javax.annotation.Resources;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
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
    public void test(){

        ICalc c = new CalcImpl();

        //加载本类的字节码
        ClassLoader loader = TestIcalc.class.getClassLoader();

        //加载类的接口
        Class[] interfaces = {ICalc.class};

        //创建代理类对象
        InvocationHandler handler = new MyHandler(c);

        ICalc proxy = (ICalc) Proxy.newProxyInstance(loader, interfaces, handler);

        //执行代理类的方法(MyHandler中的invoke方法)
//        proxy.add(12,1);
        proxy.sub(1,2);
    }



}
