package org.billing.jlab.spring.ch4.beanfactory;

import org.springframework.stereotype.Service;

/**
 * Реализуем смысл жизни для библиофила
 * аннотация Service("oracle") используется для примера с внедрением в InjectSimple, а не для XmlConfigWithBeanFactory
 */

@Service("oracle")
public class BookwormOracle implements Oracle {

    @Override
    public String defineMeaningOfLife() {
        return "Энциклопедия пустая трата денег - используй Интернет";
    }
}
