package compulsory;

import compulsory.baseClasses.Classifiable;
import compulsory.baseClasses.Location;

public class Church extends Location implements Classifiable {
    int rank = 10;

    public Church(String name, int rank) {
        this.rank = rank;
        this.name = name;
    }

    @Override
    public int getRank() {
        return rank;

    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }
}
