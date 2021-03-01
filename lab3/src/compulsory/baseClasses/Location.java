package compulsory.baseClasses;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// nu merg facute interfaces ca efectiv n ar avea sens
public abstract class Location {
    protected String name = new String("Generic");
    protected String description = new String("Generic description");
    protected int[] coordinates = {0, 0};
    protected Map<Location, Integer> travelTimes = new HashMap<Location, Integer>();
    protected String city;
    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        if(coordinates.length == 2)
            this.coordinates = coordinates;
        else System.out.println("invalid new coordinates for object " + this.name);
    }

    public Map<Location, Integer> getTravelTimes() {
        return travelTimes;
    }


    public void addTravelCost(Location to, Integer cost){
        travelTimes.put(to, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return Objects.equals(name, that.name) && Arrays.equals(coordinates, that.coordinates) && Objects.equals(city, that.city);
    }

}
