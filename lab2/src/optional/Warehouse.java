package optional;

/**
 * warehouse class derived from SourceAbstract
 * warehouse usually has more resources stored than a factory
 */
public class Warehouse extends SourceAbstract{
    /**
     * copy constructor
     * @param arg obj to be copied
     */
    public Warehouse(SourceAbstract arg) {
        super(arg);
    }

    /**
     * constructor
     * @param name name of warehouse
     * @param capacity capacity of warehouse
     */
    public Warehouse(String name, int capacity){
        super(name, capacity);
    }

    /**
     * override of the toString() method
     * @return string formatting of an object
     */
    @Override
    public String toString() {
        return "Warehouse{" +
                "name='" + name + '\'' +
                ", supplyCapacity=" + supplyCapacity +
                "}\n";
    }

}
