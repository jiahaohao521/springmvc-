package com.jiahao.Interceptor.six;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 对于多个拦截器实现
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
    public void test() {
        List<Interceptor> list = new ArrayList<>();
        list.add(new A());
        list.add(new B());
        list.add(new C());

        ICalc calc = new CalcImpl();
        //遍历list，执行多次代理对象
        for (int i = 0;i< list.size(); i ++ ){
            ICalc proxy = (ICalc) MyProxy.getProxy(calc,list.get(i));
            proxy.add(1,3);
        }
    }



}
