package optional;

import java.io.File;
import java.io.IOException;

public class PlayCommand extends generalCommand{
    static public void playThing(CatalogOptional o, String itemName) throws RuntimeException {
        var what = o.getItem(itemName);
        File f = new File(what.getPath());
        try {
            o.desktopInstance.open(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
