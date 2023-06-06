package org.billing.jlab.patterns.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;

// прокси-класс, который будет перехватывать вызов метода sayHello()
public class TestCGLib {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();

        // Класс Enhancer позволяет нам создавать прокси, динамически расширяя класс,
        // используя метод setSuperclass() из классаEnhancer
        enhancer.setSuperclass(PersonService.class);

        // FixedValue - это интерфейс обратного вызова, который просто перехватывает вызов  и
        // возвращает своё значение из проксированного метода.
        // Недостатки в том, что мы не можем решить, какой метод прокси-сервер должен перехватывать.
        enhancer.setCallback(new FixedValue() {
            public Object loadObject() throws Exception {
                return "Hello Tom!";
            }
        });
        //enhancer.setCallback((FixedValue) () -> "Hello Tom!");

        PersonService proxy1 = (PersonService) enhancer.create();
        String res = proxy1.sayHello("xxx"); // "Hello Tom!"
        System.out.println(res);

        //////////////////////////////////////////////////////////////////
        Enhancer enhancer1 = new Enhancer();
        enhancer1.setSuperclass(PersonService.class);

        enhancer1.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
            // method.getDeclaringClass() возвращает класс, к которому принадлежит метод,
            // например, toString() будет принадлежать Object, а  sayHello() будет принадлежать PersonService.class
            // Итого: перехватываем все методы объявленные не в Object, т.е. toString(), hashCode() не будут перехвачены
            // а также, метод должен возвращать String, т.е. lengthOfName() не будет перехвачен
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello Mike!";
            } else {
                // вызов метода суперкласса, т.е. метода нашего исходного класса, т.к. poroxy наследник
                return proxy.invokeSuper(obj, args1);
            }
        });

        PersonService proxy = (PersonService) enhancer1.create();
        System.out.println(proxy.sayHello(null)); //  "Hello Mike!"
        System.out.println(proxy.lengthOfName("Mary")); // 4
    }
}
