package compulsory.baseClasses;

public interface Payable {
    public int getEntryFee();
    public void setEntryFee(int entryFee);
}

/*
protected int entryFee = 0;
    public int getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(int entryFee) {
        this.entryFee = entryFee;
    }

    public Payable(String name, int entryFee){
        this.name = name;
        this.entryFee = entryFee;
    }
 */
