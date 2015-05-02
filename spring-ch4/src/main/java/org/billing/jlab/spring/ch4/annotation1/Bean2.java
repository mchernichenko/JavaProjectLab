package org.billing.jlab.spring.ch4.annotation1;

import org.springframework.stereotype.Service;

@Service("bean2")
public class Bean2
{
    public String sayHello() {
        return "I am bean2";
    }
}
