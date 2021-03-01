package compulsory.baseClasses;

import java.time.LocalTime;

public interface Visitable {
    LocalTime setOpeningHours(LocalTime hrs);
    LocalTime getOpeningHours();
}
/*
    String openingHours = null;
    void setOpeningHours(String hrs){
        //should do a check here
        openingHours = hrs;
    }
    String getOpeningHours(){
        return new String(openingHours);
    }

    public Visitable(String name, String openingHours){
        this.name = name;
        this.openingHours = openingHours;
    }

 */