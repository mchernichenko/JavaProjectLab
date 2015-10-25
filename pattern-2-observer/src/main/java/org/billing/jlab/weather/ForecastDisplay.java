package org.billing.jlab.weather;

/**
 * Интерфейс для отображения показателей метеостанции: Прогноз погоды на основе изменения давления
 */

public class ForecastDisplay implements Observer, DisplayElement {
	private float currentPressure = 29.92f;  
	private float lastPressure;
	private WeatherData weatherData;

	/**
	 * При создании объекта сразу подписываемся на изменения WeatherData
	 * @param weatherData
	 */
	public ForecastDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	public void update(float temp, float humidity, float pressure) {
                lastPressure = currentPressure;
		currentPressure = pressure;

		display();
	}

	public void display() {
		System.out.print("Прогноз погоды: ");
		if (currentPressure > lastPressure) {
			System.out.println("Давление увеличивается. Погода улучшается!");
		} else if (currentPressure == lastPressure) {
			System.out.println("Давление не изменилось");
		} else if (currentPressure < lastPressure) {
			System.out.println("Давление уменьшается. Похолодание и дождь!");
		}
	}
}
