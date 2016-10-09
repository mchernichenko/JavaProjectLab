package org.billing.jlab.spring.ch4.annotation;

import org.springframework.stereotype.Service;
import org.billing.jlab.spring.ch4.beanfactory.Oracle;

import javax.annotation.Resource;

/**
 *
 */
@Service("injectRef")
public class InjectRef {

    private Oracle oracle;

    @Resource(name="oracle")
    public void setOracle(Oracle oracle) {
        this.oracle = oracle;
    }

    public String toString() {
        return oracle.defineMeaningOfLife();
    }
}
