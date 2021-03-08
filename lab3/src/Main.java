import compulsory.*;
import compulsory.baseClasses.Location;
import compulsory.baseClasses.Visitable;
import optional.CityOptional;
import optional.TravelPlan;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var v1 = new Hotel("Hotel", 15);
        var v2 = new Museum("Museum A", LocalTime.of(9, 30));
        var v3 = new Museum("Museum B", LocalTime.of(9, 00));
        var v4 = new Church("Church A", 2);
        var v5 = new Church("Church B", 1);
        var v6 = new Restaurant("Restaurant", 16);
        v2.setClosingHours(LocalTime.of(19, 30));
        v3.defaultHrs();
        v1.addTravelCost(v2, 10);
        v1.addTravelCost(v3, 50);
        v2.addTravelCost(v3, 20);
        v3.addTravelCost(v2, 20);
        v2.addTravelCost(v4, 20);
        v2.addTravelCost(v5, 10);
        v3.addTravelCost(v4, 20);
        v4.addTravelCost(v5, 30);
        v5.addTravelCost(v4, 30);
        v4.addTravelCost(v6, 10);
        v5.addTravelCost(v6, 20);
        var city1 = new CityOptional("Iasi");
        city1.addLocation(v1);
        city1.addLocation(v2);
        city1.addLocation(v3);
        city1.addLocation(v4);
        city1.addLocation(v5);
        city1.addLocation(v6);
        System.out.println(city1);
        city1.displayVisitableByOpeningHrs();
        System.out.println(Visitable.getVisitingDuration(v2).toHours());
        List<Location> preferences = new ArrayList<Location>();
        preferences.add(v2);
        preferences.add(v1);
        preferences.add(v3);
        preferences.add(v4);
        preferences.add(v5);
        preferences.add(v6);
        var travelP = new TravelPlan(city1, preferences);
        travelP.computeShortestPath(v3, v2);
    }
}
