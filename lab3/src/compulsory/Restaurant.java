package compulsory;

import compulsory.baseClasses.Location;
import compulsory.baseClasses.Payable;

public class Restaurant extends Location implements Payable {
    int entryFee = 0;

    public Restaurant(String name, int entryFee){
        this.name = name;
        this.entryFee = entryFee;
    }

    @Override
    public int getEntryFee() {
        return entryFee;

    }

    public void setEntryFee(int entryFee) {
        this.entryFee = entryFee;

    }
}
