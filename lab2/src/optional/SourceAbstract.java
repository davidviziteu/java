package optional;

import compulsory.Source;

import java.security.PublicKey;
import java.util.Objects;

public abstract class SourceAbstract {
    String name;
    int supplyCapacity;

    public SourceAbstract(String name, int capacity) {
        this.name = name;
        this.supplyCapacity = capacity;
    }

    public int getSupplyCapacity() {
        return supplyCapacity;
    }

    public void setSupplyCapacity(int supplyCapacity) {
        this.supplyCapacity = supplyCapacity;
    }

    public String getName() {
        return name;
    }

    public void setNewName(String name) {
        this.name = name;
    }

    public SourceAbstract(SourceAbstract arg){
        name = arg.name;
        supplyCapacity = arg.supplyCapacity;
    }

    @Override
    abstract public String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceAbstract that = (SourceAbstract) o;
        return supplyCapacity == that.supplyCapacity && Objects.equals(name, that.name);
    }
}
