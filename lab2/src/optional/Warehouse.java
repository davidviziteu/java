package optional;

public class Warehouse extends SourceAbstract{

    public Warehouse(SourceAbstract arg) {
        super(arg);
    }
    public Warehouse(String name, int capacity){
        super(name, capacity);
    }
    @Override
    public String toString() {
        return "Warehouse{" +
                "name='" + name + '\'' +
                ", supplyCapacity=" + supplyCapacity +
                "}\n";
    }

}
