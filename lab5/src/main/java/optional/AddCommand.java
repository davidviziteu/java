package optional;

import compulsory.CatalogItem;
import compulsory.InvalidData;

import java.io.File;
import java.io.IOException;

public class AddCommand implements generalCommand {
    CatalogOptional o;
    CatalogItem newItem;
    public AddCommand(CatalogOptional o, CatalogItem newItem) throws RuntimeException{
       this.o = o;
       this.newItem = newItem;
    }

    @Override
    public void execute() throws IOException, RuntimeException {
        if(o.items.contains(newItem))
            throw new InvalidData("Already existing song: ", newItem);
        File f = new File(newItem.getPath());

        if(f.exists() && !f.isDirectory())
            o.items.add(newItem);
        else
            throw new InvalidData("path error");
    }
}
