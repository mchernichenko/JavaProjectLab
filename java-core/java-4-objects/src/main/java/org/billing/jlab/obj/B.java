package org.billing.jlab.obj;

import java.util.Arrays;

public class B {

    public static void main (String args[]){

        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(2));


        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        int a = -3;
        int b = -a;
        System.out.println( a+ "   "+b);

    }
}
