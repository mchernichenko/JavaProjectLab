package org.billing.jlab.spring.ch4;

/**
 * Шаблон проектирования Singleton
 * Использование шаблона увеличивает степень связанности, т.е. код приложения должен всегда явно знать о классе Singleton чтобы получить его экзампляр
 *
 *
 */
public class Singleton {

    private static Singleton instance;
    
    static {
        instance = new Singleton();
    }
    
    public static Singleton getInstance() {
        return instance;
    }	
	
}
