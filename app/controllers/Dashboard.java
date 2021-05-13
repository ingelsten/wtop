package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller

{
  public static void index()
  {
    Logger.info("Rendering Admin");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render ("dashboard.html", stations);
  }
  public static void deleteStation (Long id)
  {
    Station station = Station.findById(id);
    Logger.info ("Removing" + station.name);
    Member member = Accounts.getLoggedInMember();
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect ("/dashboard");
  }
  public static void addStation (String name)
  {
    Station station = new Station (name);
    Logger.info ("Adding a new station called " + name);
    Member member = Accounts.getLoggedInMember();
    member.stations.add(station);
    member.save();
    station.save();
    redirect ("/dashboard");
  }
}



