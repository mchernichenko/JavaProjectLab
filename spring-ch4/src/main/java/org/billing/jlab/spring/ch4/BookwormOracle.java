package org.billing.jlab.spring.ch4;

/**
 * Реалтизуем смысл жизни для библиофила
 */
public class BookwormOracle implements Oracle {

    @Override
    public String defineMeaningOfLife() {
        return "Энциклопедия пустая трата денег - используй Интернет";
    }
}
