package com.jiahao.Interceptor.six;

public class CalcImpl implements ICalc {
    @Override
    public int add(int a, int b) {
        int r = a + b;
        System.out.println("add方法。。。");
        return r;
    }

    @Override
    public int sub(int a, int b) {
        int r = a + b;
        System.out.println("sub方法。。。");
        return r;
    }

    @Override
    public int mul(int a, int b) {
        int r = a + b;
        System.out.println("mul方法。。。");
        return r;
    }

    @Override
    public int div(int a, int b) {
        int r = a + b;
        System.out.println("div方法。。。");
        return r;
    }
}
