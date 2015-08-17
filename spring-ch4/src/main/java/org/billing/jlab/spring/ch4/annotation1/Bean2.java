package org.billing.jlab.spring.ch4.annotation1;

import org.springframework.stereotype.Service;

/**
 * Этот класс нужен для примера исключения  сканирования компонентов
 * см. app-context-annotetion.xml:  <context:exclude-filter type="assignable" expression="org.billing.jlab.spring.ch4.annotation1.Bean2"/>
 */

@Service("bean2")
public class Bean2
{
    public String sayHello() {
        return "I am bean2";
    }
}
