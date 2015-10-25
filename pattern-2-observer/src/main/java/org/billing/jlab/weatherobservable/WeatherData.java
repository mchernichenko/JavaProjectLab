package org.billing.jlab.weatherobservable;
	
import java.util.Observable;

/**
 * Методы добавления/даления/нотификации уже реализованы в Observable. Здесь они не нужны как в случае с собственной реализации патерна
 */

public class WeatherData extends Observable {
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() { }
	
	public void measurementsChanged() {
		/* установка флага changed. Позволяет оптимизировать процесс оповещения, например, оповещать не каждый раз когда
		меняется меняется, а когда она меняется, напримет на 0.1 градуса
		Это позволяет избежать слишком частых оповещений
		*/
		setChanged();

		/* оповещение наблюдателей только при установленном флаге changed. после оповещения флаг опять сбрасывается
		  В метод можно передать любой объект, который передается в метод update для дальнейшей обработке на клиенте
		 */
		notifyObservers();
	}
	
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
	
	public float getTemperature() {
		return temperature;
	}
	
	public float getHumidity() {
		return humidity;
	}
	
	public float getPressure() {
		return pressure;
	}
}
