package org.billing.jlab.spring.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Пример создания неодиночных бинов
 *
 */
public class NonSingleton {

	public static void main(String[] args) {

    	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-xml.xml");
		ctx.refresh();

        // получаем экзампляры объектов с использованием одного и того же имени бина
        // область действия бина на уровне прототипа заставляет Spring создавать новый экзампляр бина каждый раз, когда он запрашивается приложением
        String s1 = (String) ctx.getBean("nonSingleton");
        String s2 = (String) ctx.getBean("nonSingleton");
        
        System.out.println("Identity Equal?: " + (s1 ==s2));
        System.out.println("Value Equal:? " + s1.equals(s2));
        System.out.println(s1);
        System.out.println(s2);		
	}

}
