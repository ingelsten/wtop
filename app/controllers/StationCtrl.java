package controllers;

import java.util.List;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import static utils.StationAnalytics.codeToText;

public class StationCtrl extends Controller {

    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        String weatherCode = "100";
        int beauCode = 0;
        String compassDirection;
        String windChillTemp;
        Reading latestRead = StationAnalytics.getLatestReading(station.readings);
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


        //      Logger.info("Latest read in Celcius= " + latestRead.temperature + "Latest pressure= " + latestRead.pressure);
        render("station.html", station, latestRead, weatherCode, beauCode, compassDirection, windChillTemp);
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
