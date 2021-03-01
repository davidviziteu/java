package compulsory;

import compulsory.baseClasses.Location;

import java.util.*;

public class City {
    List<Location> locationList = new ArrayList<Location>();
    String name;
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
}
