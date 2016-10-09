package org.billing.jlab.weather;

// просто добавляет еще одного наблюдателя для отображения теплогово индекса

public class WeatherStationHeatIndex {

	public static void main(String[] args) {
		// наблюдаемый объект
		WeatherData weatherData = new WeatherData();

		// 4 клиентских визуальных приложений которые подписываются на изменения в WeatherData
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

		// имитируем изменение состояния объекта метеостанции
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
