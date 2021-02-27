package optional;

public class Factory extends SourceAbstract{

    public Factory(SourceAbstract arg) {
        super(arg);
    }

    public Factory(String name, int capacity){
        super(name, capacity);
    }

    @Override
    public String toString(){
        return "Factory{" +
                "name='" + name + '\'' +
                ", supplyCapacity=" + supplyCapacity +
                "}\n";
    }

}
