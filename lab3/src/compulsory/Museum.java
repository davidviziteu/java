package compulsory;

import compulsory.baseClasses.Location;
import compulsory.baseClasses.Visitable;

import java.time.LocalDate;
import java.time.LocalTime;

public class Museum extends Location implements Visitable {
    LocalTime openingHours;

    @Override
    public LocalTime setOpeningHours(LocalTime hrs) {
        return openingHours = hrs;
    }

    @Override
    public LocalTime getOpeningHours() {
        return openingHours;
    }

    public Museum(String name, LocalTime openingHours){
        this.name = name;
        this.openingHours = openingHours;
    }
}

