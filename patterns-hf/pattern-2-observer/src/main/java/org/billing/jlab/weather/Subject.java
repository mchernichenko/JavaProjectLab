package org.billing.jlab.weather;

/**
 * Интерфейс субъекта, используется для регистрации наблюдатедей, а также исключения из их списка
 */
public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
