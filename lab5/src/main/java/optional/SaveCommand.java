package optional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand implements generalCommand {
    CatalogOptional o;
    String fileName;
     public SaveCommand(CatalogOptional o, String fileName){
        this.o = o;
        this.fileName = fileName;
    }

    @Override
    public void execute() throws IOException, RuntimeException {
        try {
            File f = new File(fileName);
            if (f.createNewFile()) {
                o.items.forEach(i ->{
                    try {
                        FileWriter w = new FileWriter(fileName);
                        w.write(i.getName() + " " + i.getClass().getName() + " " + i.getPath());
                        w.close();
                        System.out.println("Successfully exported to file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                });
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
