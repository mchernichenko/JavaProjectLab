package org.billing.jlab.weather;

public class WeatherStation {

	public static void main(String[] args) {
		// наблюдаемый объект
		WeatherData weatherData = new WeatherData();

		// 3 клиентских визуальных приложений которые подписываются на изменения в WeatherData
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

		// иммитируем изменение состояния объекта метеостанции
		weatherData.setMeasurements(80, 65, 30.4f);
		System.out.println("--------------------------");
		weatherData.setMeasurements(82, 70, 29.2f);
		System.out.println("--------------------------");
		weatherData.setMeasurements(78, 90, 29.2f);
		System.out.println("--------------------------");
	}
}
