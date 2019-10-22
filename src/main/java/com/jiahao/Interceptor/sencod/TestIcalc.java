package com.jiahao.Interceptor.sencod;

public class TestIcalc {

    public static void main(String[] args) {
        ICalc iCalc = new CalcImpl();
        int r = iCalc.add(1, 3);
        System.out.println(r);
    }
}
