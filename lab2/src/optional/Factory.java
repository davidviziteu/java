package optional;


/**
 * Factory class derived from SourceAbstract
 * Factory usually has less resources stored than a warehouse
 */
public class Factory extends SourceAbstract{
    /**
     * copy constructor
     * @param arg obj to be copied
     */
    public Factory(SourceAbstract arg) {
        super(arg);
    }
    /**
     * constructor
     * @param name name of factory
     * @param capacity capacity of factory
     */
    public Factory(String name, int capacity){
        super(name, capacity);
    }
    /**
     * override of the toString() method
     * @return string formatting of an object
     */
    @Override
    public String toString(){
        return "Factory{" +
                "name='" + name + '\'' +
                ", supplyCapacity=" + supplyCapacity +
                "}\n";
    }

}
