package compulsory.baseClasses;

import java.time.Duration;
import java.time.LocalTime;

public interface Visitable {
    LocalTime getOpeningHours();
    LocalTime getClosingHours();
    void setOpeningHours(LocalTime hrs);
    void setClosingHours(LocalTime hrs);

    default void defaultHrs(){
        this.setOpeningHours(LocalTime.of(9, 30));
        this.setClosingHours(LocalTime.of(20, 00));
    }

    public static Duration getVisitingDuration(Location arg) {
        if(arg instanceof Visitable)
            return Duration.between(((Visitable) arg).getOpeningHours(), ((Visitable) arg).getClosingHours());
        return Duration.ZERO;
    }
}
