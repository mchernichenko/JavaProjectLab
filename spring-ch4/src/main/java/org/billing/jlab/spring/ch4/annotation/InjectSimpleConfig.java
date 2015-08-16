package org.billing.jlab.spring.ch4.annotation;

import org.springframework.stereotype.Component;

/**
 * Конфигурационный класс, содержащий значения, предназначенные для внедрения в другие бины
 * Вместо @Service используется аннотация @Component
 * Обе аннотации дают одинаковый эффект, но т.к. класс хранит только конфигурацию и не предоставляет бизнес-службу другим уровням приложения,
 * использование @Component имеет больший смысл
 *
 */

@Component("injectSimpleConfig")
public class InjectSimpleConfig {

    private String name = "John Smith";

    private int age = 35;

    private float height = 1.78f;

    private boolean programmer = true;

    private Long ageInSeconds = 1103760000L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isProgrammer() {
		return programmer;
	}

	public void setProgrammer(boolean programmer) {
		this.programmer = programmer;
	}

	public Long getAgeInSeconds() {
		return ageInSeconds;
	}

	public void setAgeInSeconds(Long ageInSeconds) {
		this.ageInSeconds = ageInSeconds;
	}	
	
}
