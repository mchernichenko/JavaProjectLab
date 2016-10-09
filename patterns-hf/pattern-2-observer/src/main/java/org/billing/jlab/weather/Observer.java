package org.billing.jlab.weather;

/**
 * Интерфейс наблюдателя, который содержит единственный метод, который вызывается при изменения состояния субъекта
 */
public interface Observer {
	public void update(float temp, float humidity, float pressure);
}
