package optional;

import java.io.File;
import java.io.IOException;

public class PlayCommand implements generalCommand {
    CatalogOptional o;
    String itemName;
    public  PlayCommand(CatalogOptional o, String itemName) throws RuntimeException {
        this.o = o;
        this.itemName = itemName;
    }

    @Override
    public void execute() throws IOException, RuntimeException {
        var what = o.getItem(itemName);
        File f = new File(what.getPath());
        try {
            o.desktopInstance.open(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
