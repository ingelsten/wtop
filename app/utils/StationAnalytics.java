package utils;

import models.Reading;

import java.util.List;

import java.text.DecimalFormat;

public class StationAnalytics {


    private static int windDirection;

    public static Reading getLatestReading(List<Reading> readings) {

        Reading latestRead = null;

        if (readings.size() > 0) {
            latestRead = readings.get(0);
            for (Reading reading : readings) {
                if (reading.id > latestRead.id) {
                    latestRead = reading;
                }
            }
        }
        return latestRead;
    }

    public static String codeToText(int code) {
        switch (code) {

            case 100:
                return "Clear";
            case 200:
                return "Partial Clouds";
            case 300:
                return "Cloudy";
            case 400:
                return "Light Showers";
            case 500:
                return "Heavy Showers";
            case 600:
                return "Rain";
            case 700:
                return "Snow";
            case 800:
                return "Thunder";
            default:
                return "Crap Data";
        }
    }

    public static int windCode(double windSpeed) {
        if (windSpeed <= 1) {
            return 0;
        }
        else if (windSpeed >1 && windSpeed <=5) {
            return 1;
        }
        else if (windSpeed >=6 && windSpeed <=11) {
            return 2;
        }
        else if (windSpeed >12 && windSpeed <=19) {
            return 3;
        }
        else if (windSpeed >20 && windSpeed <=28) {
            return 4;
        }
        else if (windSpeed >29 && windSpeed <=38) {
            return 5;
        }
        else if (windSpeed >39 && windSpeed <=49) {
            return 6;
        }
        else if (windSpeed >50 && windSpeed <=61) {
            return 7;
        }
        else if (windSpeed >=62 && windSpeed <=74) {
            return 8;
        }
        else if (windSpeed >75 && windSpeed <=88) {
            return 9;
        }
        else if (windSpeed >89 && windSpeed <=102) {
            return 10;
        }
        else if (windSpeed >103) {
            return 11;
        }
        return 0;
    }


    public static String compassDirection(int windDirection) {


        if ((windDirection >= 348.75) && (windDirection <= 360) ||
                (windDirection >= 0) && (windDirection <= 11.25)) {
            return "N";
        } else if ((windDirection >= 11.25) && (windDirection <= 33.75)) {
            return "NNE";
        } else if ((windDirection >= 33.75) && (windDirection <= 56.25)) {
            return "NE";
        } else if ((windDirection >= 56.25) && (windDirection <= 78.75)) {
            return "ENE";
        } else if ((windDirection >= 78.75) && (windDirection <= 101.25)) {
            return "E";
        } else if ((windDirection >= 101.25) && (windDirection <= 123.75)) {
            return "ESE";
        } else if ((windDirection >= 123.75) && (windDirection <= 146.25)) {
            return "SE";
        } else if ((windDirection >= 146.25) && (windDirection <= 168.75)) {
            return "SSE";
        } else if ((windDirection >= 168.75) && (windDirection <= 191.25)) {
            return "S";
        } else if ((windDirection >= 191.25) && (windDirection <= 213.75)) {
            return "SSW";
        } else if ((windDirection >= 213.75) && (windDirection <= 236.25)) {
            return "SW";
        } else if ((windDirection >= 236.25) && (windDirection <= 258.75)) {
            return "WSW";
        } else if ((windDirection >= 258.75) && (windDirection <= 281.25)) {
            return "W";
        } else if ((windDirection >= 281.25) && (windDirection <= 303.75)) {
            return "WNW";
        } else if ((windDirection >= 303.75) && (windDirection <= 326.25)) {
            return "NW";
        } else if ((windDirection >= 326.25) && (windDirection <= 348.75)) {
            return "NNW";
        } else if (windDirection > 360) {
            return "CRAP DATA";
        }

        return null;
    }
//https://mkyong.com/java/java-display-double-in-2-decimal-points
    public static String windChill(double temperature, double windSpeed){

        DecimalFormat df2 = new DecimalFormat("#.##");

        double windChillTemp=0.0;

        windChillTemp = (13.12 +(0.6215 * temperature) - (11.37*Math.pow(windSpeed, 0.16) )+ (0.3965*temperature*Math.pow(windSpeed, 0.16)));

        return (df2.format(windChillTemp));
    }
}



