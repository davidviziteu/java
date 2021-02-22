package compulsory;

import java.util.Arrays;

public class Source {
    SourceType type;
    String name;
    int supplyCapacity;

    public Source(String name, SourceType type, int supplyCapacity) {
        this.type = type;
        this.name = name;
        this.supplyCapacity = supplyCapacity;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setType(SourceType type) {
        this.type = type;
    }

    public SourceType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Source{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", supplyCapacity=" + supplyCapacity +
                "}\n";
    }
}
