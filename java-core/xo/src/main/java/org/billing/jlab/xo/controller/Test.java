package org.billing.jlab.xo.controller;

public class Test{
    public static void main(String[] args) {
        System.out.println(parseFloat("5.8ffgd"));
    }

    public static float parseFloat( String s ){
        float f = 0.0f;      // 1
        try{
            f = Float.valueOf( s ).floatValue();    // 2
            return f ;      // 3
        }
        catch(NumberFormatException nfe){
            f = Float.NaN ;    // 4
         //   return f;     // 5
        }
        finally {
         //   return f;     // 6
        }
        return f ;    // 7
    }
}