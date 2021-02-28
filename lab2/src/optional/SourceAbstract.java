package optional;

import compulsory.Source;

import java.security.PublicKey;
import java.util.Objects;

/**
 * abstract class of source. children: Warehouse, Factory
 */
public abstract class SourceAbstract {
    String name;
    int supplyCapacity;

    /**
     * Constructor of class
     * @param name a name for the source
     * @param capacity capacity for the source
     */
    public SourceAbstract(String name, int capacity) {
        this.name = name;
        this.supplyCapacity = capacity;
    }

    /**
     * getter
     * @return available supply capacity of source
     */
    public int getSupplyCapacity() {
        return supplyCapacity;
    }

    /**
     * sets a new supply capacity for source. used only in the solution class when computing the solution
     * @param supplyCapacity new supply capacity for object
     */
    public void setSupplyCapacity(int supplyCapacity) {
        this.supplyCapacity = supplyCapacity;
    }

    /**
     * getter
     * @return return the name of the supplier
     */
    public String getName() {
        return name;
    }

    /**
     * setter
     * @param name sets new name for supplier
     */
    public void setNewName(String name) {
        this.name = name;
    }

    /**
     * copy constructor. maybe needed
     * @param arg object to be copied
     */
    public SourceAbstract(SourceAbstract arg){
        name = arg.name;
        supplyCapacity = arg.supplyCapacity;
    }

    /**
     * override of the toString() method
     * @return string formatting of the object
     */
    @Override
    abstract public String toString();

    /**
     * override of the equals() method
     * @param o object to be compared to
     * @return true / false if the object has the same name
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceAbstract that = (SourceAbstract) o;
        return Objects.equals(name, that.name);
    }
}
