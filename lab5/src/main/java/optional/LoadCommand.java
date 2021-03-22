package optional;

import compulsory.InvalidData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadCommand implements generalCommand{
    CatalogOptional o;
    String fromFilePath;
    public LoadCommand(CatalogOptional o, String fromFilePath) {
        this.o = o;
        this.fromFilePath = fromFilePath;
    }

    @Override
    public void execute() throws IOException, RuntimeException{
        File f = new File(fromFilePath);
        if(f.exists() && !f.isDirectory()){
            BufferedReader in = new BufferedReader(new FileReader(fromFilePath));
            String text;

            while (in.ready()) {
                text = in.readLine();
                var instructions = text.split(" ");
                var result = CatalogOptional.getObject(instructions);
                if(result == null) {
                    System.err.println("line ignored: " + text);
                    continue;
                }
                o.items.add(result);
            }
            in.close();
        }
        else
            throw new InvalidData("path error");
        System.out.println("Finished loading. Use the command <list> to see the items");
    }
}
