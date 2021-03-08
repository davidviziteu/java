package compulsory;

import compulsory.baseClasses.Location;
import compulsory.baseClasses.Visitable;

import java.time.LocalDate;
import java.time.LocalTime;

public class Museum extends Location implements Visitable {
    LocalTime openingHours, closingHours;
    @Override
    public void setOpeningHours(LocalTime hrs) {
        openingHours = hrs;
    }
    @Override
    public void setClosingHours(LocalTime hrs) {
        closingHours = hrs;
    }

    @Override
    public LocalTime getOpeningHours() {
        return openingHours;
    }

    @Override
    public LocalTime getClosingHours() {
        return closingHours;
    }

//    @Override
//    public void defaultHrs() {
//        openingHours = LocalTime.of(9, 30);
//        closingHours = LocalTime.of(20, 00);
//    }

    public Museum(String name, LocalTime openingHours){
        this.name = name;
        this.openingHours = openingHours;
    }
}

