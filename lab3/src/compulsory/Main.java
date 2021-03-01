package compulsory;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        var v1 = new Hotel("Hotel", 15);
        var v2 = new Museum("Museum A", LocalTime.of(9, 30));
        var v3 = new Museum("Museum B", LocalTime.of(9, 00));
        var v4 = new Church("Church A", 2);
        var v5 = new Church("Church B", 1);
        var v6 = new Restaurant("Restaurant", 16);
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
        var city1 = new City("Iasi");
        city1.addLocation(v1);
        city1.addLocation(v2);
        city1.addLocation(v3);
        city1.addLocation(v4);
        city1.addLocation(v5);
        city1.addLocation(v6);
        System.out.println(city1);
    }
}
