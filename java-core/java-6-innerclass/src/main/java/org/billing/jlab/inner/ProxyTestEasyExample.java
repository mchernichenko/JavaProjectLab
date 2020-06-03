package org.billing.jlab.inner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

public class ProxyTestEasyExample {
    public static void main(String[] args) {
        InvocationHandler handler = new MyProxy("qwerty"); //   делаем обёртку над объектом, в данном случае String

        // перечисляем интерфейсы, методы которых будут перехватываться прокси-объектом
        // предполагается, что исходный объект их тоже реализует. Если мы хотим ничего не забыть и перехватывать всё, то список интерфейсов лучше не задавать вручную
        //Class[] interfaces = new Class[]{Comparable.class, Callable.class};
        Class[] interfaces = String.class.getInterfaces();

        /* собственно создание прокси, его вид https://restless-man.livejournal.com/24320.html
           он реализует все методы указанных интерфейсов, а также методы класса Object
         */
        Object proxy = Proxy.newProxyInstance(null, interfaces, handler);

        // на прокси теперь можно вызвать любой метод интерфейса или класса Object, как и у оригинального класса
        System.out.println(((Comparable) proxy).compareTo("qwerty_1"));
    }


    static class MyProxy implements InvocationHandler {
        Object target;

        public MyProxy(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(args); // "qwerty_1"
            return method.invoke(target, args); // method - compareTo, target - исходный обект "qwerty", т.е. по сути вызывается "qwerty".compareTo("qwerty_1")
        }
    }
}
