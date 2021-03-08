package compulsory;

import compulsory.baseClasses.Location;

import java.util.*;

public class City {
    protected List<Location> locationList = new ArrayList<Location>();
    protected String name;
    public City(String name){
        this.name = name;
    }

    public void addLocation(Location newLocation){
        newLocation.setCity(name);
        locationList.add(newLocation);
    }

    @Override
    public String toString() {
        StringBuilder locationMap = new StringBuilder(new String(""));
        for(var l : locationList)
            for(var to : l.getTravelTimes().entrySet())
                locationMap
                        .append(l.getName())
                        .append(" -> ")
                        .append(to.getKey().getName())
                        .append(": ")
                        .append(to.getValue())
                        .append("\n");


        return "City{\n" +
                "name=" + name +
                "\nlocation map:\n" + locationMap +
                "}";
    }
    public int getLocationIdx(Location l){
        for(var i = 0; i < locationList.size(); ++i){
            if(locationList.get(i).equals(l))
                return i;
        }
        return -1;
    }
}
