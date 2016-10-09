package org.billing.jlab.oop;

import org.billing.jlab.oop.pak2.B;
import org.junit.Test;

/**
 * Unit tests.
 */
public class OppTest
{
    @Test
    public void testProtectMethod() throws Exception {
        B b = new B();
        //
        //b.packagePrivateMethod(2, 3);
       // b.runProtectedMethod(2,3);
        // вызов данного метода приведёт к зацикливанию, т.к. b.protectedMethod вызывает runProtectedMethod из класса A,
        // который в свою очередь вызывает опять protectedMethod из класса B, т.к. вызывающий класс это класс В, а он переопределён в классе B.
        b.func();
    }
}
