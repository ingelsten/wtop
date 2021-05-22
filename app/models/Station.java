package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


// Data held for Stations and passed to SQL db.
@Entity
public class Station extends Model {
  public String name;
  public Float latitude;
  public Float longitude;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();


  public Station(String name, float latitude, float longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }


}


