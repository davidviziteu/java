package optional;

import compulsory.City;
import compulsory.baseClasses.Location;
import compulsory.baseClasses.Payable;
import compulsory.baseClasses.Visitable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityOptional extends City {
    public CityOptional(String name) {
        super(name);
    }
    public void displayVisitableByOpeningHrs(){
        var visitables = new ArrayList<Location>(locationList);
        visitables.clear();

        this.locationList.forEach(l -> {
            if( l instanceof Visitable && !(l instanceof Payable))
                visitables.add(l);
        });

        visitables.sort((a, b) -> {
            var aa = (Visitable) a;
            var bb = (Visitable) b;
            return aa.getOpeningHours().compareTo(bb.getOpeningHours());
        });
        System.out.println("visitable locations:");
        visitables.forEach(v -> System.out.println(v.getName()));
    }
    public int getLocationCount(){
        return this.locationList.size();
    }
    public List<Location> getLocationList(){
        return new ArrayList<>(this.locationList);
    }
}
