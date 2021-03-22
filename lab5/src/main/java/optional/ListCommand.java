package optional;

import java.io.IOException;

public class ListCommand implements generalCommand{
    CatalogOptional o;
    public  ListCommand(CatalogOptional o){
        this.o = o;

    }

    @Override
    public void execute() throws IOException, RuntimeException {
        if(o.items.size() == 0) {
            System.out.println("No items in catalog");
            return;
        }
        o.items.forEach(i -> System.out.println(i.toString()));
    }
}
