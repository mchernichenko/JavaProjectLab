package org.billing.jlab.spring.ch4.hierarchical;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Чтобы вложить один контекст в другой необходимо вызвать метод setParent в дочернем контексте
 * Это позволяет разделять конфигурацию на отдельные файлы.
 * Удобно для крупных проектов, содержащих множество бинов.
 */
public class HierarchicalAppContextUsage {

	public static void main(String[] args) {

    	GenericXmlApplicationContext parent = new GenericXmlApplicationContext();
    	parent.load("classpath:META-INF/spring/parent.xml");
    	parent.refresh();   			
		
    	GenericXmlApplicationContext child = new GenericXmlApplicationContext();
    	child.load("classpath:META-INF/spring/app-context-xml.xml");
		// вложение контекста
    	child.setParent(parent);
    	child.refresh();   		

		// target1 и target3 получают ссылку на бины в родительском контексте, а target2 на ссылку в дочернем контексте
        SimpleTarget target1 = (SimpleTarget) child.getBean("target1");
        SimpleTarget target2 = (SimpleTarget) child.getBean("target2");
        SimpleTarget target3 = (SimpleTarget) child.getBean("target3");

        System.out.println(target1.getVal());
        System.out.println(target2.getVal());
        System.out.println(target3.getVal());    	
    	
	}

}
