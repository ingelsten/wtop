package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

// Station Controller passing parameters thru to display in view

public class StationCtrl extends Controller {


  // Declaring variables
  public static void index(Long id) {
    Station station = Station.findById(id);
    Logger.info("Station id = " + id);
    String weatherCode;
    int beauCode;
    String compassDirection;
    String windChillTemp;
    double minTemperature;
    double maxTemperature;
    double minWindSpeed;
    double maxWindSpeed;
    double minPressure;
    double maxPressure;
    Reading latestRead = StationAnalytics.getLatestReading(station.readings);

    // Try catch statement to avoid NullPointerExeption
    // Latest reads
    // Conversions and max min values

    try {
      weatherCode = StationAnalytics.codeToText(latestRead.code);

    } catch (NullPointerException e) {
      weatherCode = "no data";
    }

    try {
      beauCode = StationAnalytics.windCode(latestRead.windSpeed);

    } catch (NullPointerException e) {
      beauCode = 0;
    }

    try {
      compassDirection = StationAnalytics.compassDirection(latestRead.windDirection);

    } catch (NullPointerException e) {
      compassDirection = "N";
    }

    try {
      windChillTemp = StationAnalytics.windChill(latestRead.temperature, latestRead.windSpeed);

    } catch (NullPointerException e) {
      windChillTemp = "0";
    }
    try {
      minTemperature = StationAnalytics.MinTemp(station.readings);
    } catch (NullPointerException e) {
      minTemperature = 0;
    }
    try {
      maxTemperature = StationAnalytics.maxTemp(station.readings);
    } catch (NullPointerException e) {
      maxTemperature = 0;
    }
    try {
      minWindSpeed = StationAnalytics.MinWind(station.readings);
    } catch (NullPointerException e) {
      minWindSpeed = 0;
    }
    try {
      maxWindSpeed = StationAnalytics.maxWind(station.readings);
    } catch (NullPointerException e) {
      maxWindSpeed = 0;
    }
    try {
      minPressure = StationAnalytics.minPressure(station.readings);
    } catch (NullPointerException e) {
      minPressure = 0;
    }
    try {
      maxPressure = StationAnalytics.maxPressure(station.readings);
    } catch (NullPointerException e) {
      maxPressure = 0;
    }


    // Loggers used during testing to ensure data is passed thru.
    //  Logger.info("Test " + minTemperature);
    //      Logger.info("Latest read in Celcius= " + latestRead.temperature + "Latest pressure= " + latestRead.pressure);

    // Below renders data to view.
    render("station.html", station, latestRead, weatherCode, beauCode, compassDirection, windChillTemp, minTemperature,
        maxTemperature, minWindSpeed, maxWindSpeed, minPressure, maxPressure);
  }


  public static void deleteReading(Long id, Long readingid) {
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info("Removing" + reading.code);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    render("station.html", station);
  }

  public static void addReading(Long id, int code, double temperature, double windSpeed, int windDirection, int pressure) {
    Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/stations/" + id);
  }
}
