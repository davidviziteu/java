package optional;

import compulsory.CatalogItem;
import compulsory.InvalidData;

import java.io.File;

public class AddCommand extends generalCommand {
    static void addThing(CatalogOptional o, CatalogItem newItem) throws RuntimeException{
        if(o.items.contains(newItem))
            throw new InvalidData("Already existing song: ", newItem);
        File f = new File(newItem.getPath());

        if(f.exists() && !f.isDirectory())
            o.items.add(newItem);
        else
            throw new InvalidData("path error");
    }
}
