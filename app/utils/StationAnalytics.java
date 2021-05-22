package utils;

import models.Reading;

import java.util.List;

import java.text.DecimalFormat;

/**
 * This class does all the analytics
 */
public class StationAnalytics {


  private static int windDirection;

  public static Reading getLatestReading(List<Reading> readings) {
    //gets the latest reading
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
    } else if (windSpeed > 1 && windSpeed <= 5) {
      return 1;
    } else if (windSpeed >= 6 && windSpeed <= 11) {
      return 2;
    } else if (windSpeed > 12 && windSpeed <= 19) {
      return 3;
    } else if (windSpeed > 20 && windSpeed <= 28) {
      return 4;
    } else if (windSpeed > 29 && windSpeed <= 38) {
      return 5;
    } else if (windSpeed > 39 && windSpeed <= 49) {
      return 6;
    } else if (windSpeed > 50 && windSpeed <= 61) {
      return 7;
    } else if (windSpeed >= 62 && windSpeed <= 74) {
      return 8;
    } else if (windSpeed > 75 && windSpeed <= 88) {
      return 9;
    } else if (windSpeed > 89 && windSpeed <= 102) {
      return 10;
    } else if (windSpeed > 103) {
      return 11;
    }
    return 0;
  }


  public static String compassDirection(int windDirection) {


    if ((windDirection >= 348.75) && (windDirection <= 360) ||
        (windDirection >= 0) && (windDirection <= 11.25)) {
      return "North";
    } else if ((windDirection >= 11.25) && (windDirection <= 33.75)) {
      return "North North East";
    } else if ((windDirection >= 33.75) && (windDirection <= 56.25)) {
      return "North East";
    } else if ((windDirection >= 56.25) && (windDirection <= 78.75)) {
      return "East North East";
    } else if ((windDirection >= 78.75) && (windDirection <= 101.25)) {
      return "East";
    } else if ((windDirection >= 101.25) && (windDirection <= 123.75)) {
      return "East South East";
    } else if ((windDirection >= 123.75) && (windDirection <= 146.25)) {
      return "South East";
    } else if ((windDirection >= 146.25) && (windDirection <= 168.75)) {
      return "South South East";
    } else if ((windDirection >= 168.75) && (windDirection <= 191.25)) {
      return "South";
    } else if ((windDirection >= 191.25) && (windDirection <= 213.75)) {
      return "South South West";
    } else if ((windDirection >= 213.75) && (windDirection <= 236.25)) {
      return "South W";
    } else if ((windDirection >= 236.25) && (windDirection <= 258.75)) {
      return "West South West";
    } else if ((windDirection >= 258.75) && (windDirection <= 281.25)) {
      return "West";
    } else if ((windDirection >= 281.25) && (windDirection <= 303.75)) {
      return "West North West";
    } else if ((windDirection >= 303.75) && (windDirection <= 326.25)) {
      return "North West";
    } else if ((windDirection >= 326.25) && (windDirection <= 348.75)) {
      return "North North West";
    } else if (windDirection > 360) {
      return "CRAP DATA";
    }

    return null;
  }

  //https://mkyong.com/java/java-display-double-in-2-decimal-points
  public static String windChill(double temperature, double windSpeed) {

    DecimalFormat df2 = new DecimalFormat("#.##");

    double windChillTemp = 0.0;

    windChillTemp = (13.12 + (0.6215 * temperature) - (11.37 * Math.pow(windSpeed, 0.16)) + (0.3965 * temperature * Math.pow(windSpeed, 0.16)));

    return (df2.format(windChillTemp));
  }


  public static double MinTemp(List<Reading> readings) {

    Reading minTempReading = null;

    if (readings.size() > 0) {
      minTempReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.temperature < minTempReading.temperature) {
          minTempReading = reading;
        }
      }
    }
    return minTempReading.temperature;
  }

  public static double maxTemp(List<Reading> readings) {

    Reading maxTempReading = null;

    if (readings.size() > 0) {
      maxTempReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.temperature > maxTempReading.temperature) {
          maxTempReading = reading;

        }
      }
    }
    return maxTempReading.temperature;
  }

  public static double MinWind(List<Reading> readings) {

    Reading minWindReading = null;

    if (readings.size() > 0) {
      minWindReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.windSpeed < minWindReading.windSpeed) {
          minWindReading = reading;
        }
      }
    }
    return minWindReading.windSpeed;
  }

  public static double maxWind(List<Reading> readings) {

    Reading maxWindReading = null;

    if (readings.size() > 0) {
      maxWindReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.windSpeed > maxWindReading.windSpeed) {
          maxWindReading = reading;
        }
      }
    }
    return maxWindReading.windSpeed;
  }

  public static double minPressure(List<Reading> readings) {

    Reading minPressure = null;

    if (readings.size() > 0) {
      minPressure = readings.get(0);
      for (Reading reading : readings) {
        if (reading.pressure < minPressure.pressure) {
          minPressure = reading;
        }
      }
    }
    return minPressure.pressure;
  }

  public static double maxPressure(List<Reading> readings) {

    Reading maxPressure = null;

    if (readings.size() > 0) {
      maxPressure = readings.get(0);
      for (Reading reading : readings) {
        if (reading.pressure > maxPressure.pressure) {
          maxPressure = reading;
        }
      }
    }
    return maxPressure.pressure;
  }
}


