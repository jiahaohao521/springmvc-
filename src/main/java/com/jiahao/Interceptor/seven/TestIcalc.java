package com.jiahao.Interceptor.seven;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 从配置文件中读取拦截器类
 */
interface Interceptor {
    //模拟springmvc拦截器中的方法
    boolean preHandle();
    void postHandle();
}

class MyProxy {

    public static Object getProxy(Object target , Interceptor i){

        ClassLoader loader = MyProxy.class.getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler handler = new MyHandler(target,i);
        Object o = Proxy.newProxyInstance(loader, interfaces, handler);
        return o;
    }

    public static Object getProxy2(Object target) throws Exception {
        List<Interceptor> list = new ArrayList<>();
        //加载配置文件
        InputStream in = TestIcalc.class.getResourceAsStream("myconfig.properties");
        Properties properties = new Properties();
        properties.load(in);

        //读取配置文件中属性的值
        String interceptor = properties.getProperty("interceptor");
        String[] split = interceptor.split(",");
        for (String ss : split){
            //加载配置文件中的拦截器，并转换成类
            Class aClass = Class.forName(ss);
            list.add((Interceptor)aClass.newInstance());
        }

        for (int i = 0 ;i < list.size() ; i ++){
            target = MyProxy.getProxy(target,list.get(i));
        }
        return target;
    }
}

class MyHandler implements InvocationHandler {

    //创建目标对象，是其与其他对类进行关联
    private Object target;

    //创建拦截器对象
    private Interceptor i ;

    public MyHandler(Object target , Interceptor i){
        this.target = target;
        this.i = i;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println(method.getName() + "方法开始");
        //判断preHandle()若为false，则不在继续执行下面方法
        boolean b = i.preHandle();
        if (!b){
            return false;
        }
        //有目标对象target调用方法
        Object r = method.invoke(target, objects);
        //调用拦截器中的方法
        i.preHandle();
        System.out.println(method.getName() + "方法结束");
        i.postHandle();
        return r;
    }
}

class A implements Interceptor {

    @Override
    public boolean preHandle() {
        System.out.println("A-----------------------preHandle");
        return true;
    }

    @Override
    public void postHandle() {
        System.out.println("A------------------------postHandle");
    }
}

class B implements Interceptor {

    @Override
    public boolean preHandle() {
        System.out.println("B-----------------------preHandle");
        return true;
    }

    @Override
    public void postHandle() {
        System.out.println("B------------------------postHandle");
    }
}

class C implements Interceptor {

    @Override
    public boolean preHandle() {
        System.out.println("C-----------------------preHandle");
        return true;
    }

    @Override
    public void postHandle() {
        System.out.println("C------------------------postHandle");
    }
}


//===============================分界线=======================================================


public class TestIcalc {

    @Test
    public void test() throws Exception {
        ICalc ca = new CalcImpl();
        ICalc proxy1 = (ICalc) MyProxy.getProxy2(ca);
        proxy1.add(1,2);
    }

}
