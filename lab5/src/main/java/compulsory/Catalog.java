package compulsory;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Catalog {
    ArrayList<CatalogItem> items = new ArrayList<>();
    Desktop desktopInstance;

    public void add(CatalogItem newItem) throws RuntimeException{
        if(items.contains(newItem))
            throw new InvalidData("Already existing song: ", newItem);
        File f = new File(newItem.path);

        if(f.exists() && !f.isDirectory())
            items.add(newItem);
        else
            throw new InvalidData("path error");

    }

    public void list(){
        if(items.size() == 0) {
            System.out.println("No items in catalog");
            return;
        }
        items.forEach(i -> System.out.println(i.toString()));
    }

    public void play(String itemName) throws RuntimeException{
        var what = this.getItem(itemName);
//        if(!(what instanceof Song))
//            throw new InvalidData("not a song", what);

        File f = new File(what.getPath());
        try {
            desktopInstance.open(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String fileName){
        try {
            File f = new File(fileName);
            if (f.createNewFile()) {
                items.forEach(i ->{
                    try {
                        FileWriter w = new FileWriter(fileName);
                        w.write(i.name + " " + i.getClass().getName() + " " + i.getPath());
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

    static CatalogItem getObject(String[] data){
        if(data.length != 3)
            return null;

        if(data[1].equals("compulsory.Song")){
            return new Song(data[0], data[2]);
        }
        if(data[1].equals("compulsory.Picture")){
            return new Picture(data[0], data[2]);
        }
        return null;
    }

    public void load(String fromFilePath) throws IOException, RuntimeException {
        File f = new File(fromFilePath);
        if(f.exists() && !f.isDirectory()){
            BufferedReader in = new BufferedReader(new FileReader(fromFilePath));
            String text;

            while (in.ready()) {
                text = in.readLine();
                var instructions = text.split(" ");
                var result = getObject(instructions);
                if(result == null) {
                    System.err.println("line ignored: " + text);
                    continue;
                }
                this.items.add(result);
            }
            in.close();
        }
        else
            throw new InvalidData("path error");
        System.out.println("Finished file. Use the command <list> to see the items");
    }

    public Catalog(Desktop desktopInstance){
        this.desktopInstance = desktopInstance;
    }

    public static void main(String[] args) {
        var desktop = Desktop.getDesktop();
        var catalog = new Catalog(desktop);
        System.out.println("Hello, enter commands:");
        while(true){
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            try {
                String text = in.readLine();
                if(text.equals("exit"))
                    return;
                if(text.equals("add song")){
                    //song
                    System.out.println("enter song name:");
                    String songName = in.readLine();
                    System.out.println("enter song path:");
                    String songPath = in.readLine();
                    var newSong = new Song(songName, songPath);
                    catalog.add(newSong);
                    System.out.println("ok");
                    continue;

                }
                if(text.equals("add picture")){
                    //song
                    System.out.println("enter picture name:");
                    String pictureName = in.readLine();
                    System.out.println("enter picture path:");
                    String picturePath = in.readLine();
                    var newPic = new Picture(pictureName, picturePath);
                    catalog.add(newPic);
                    System.out.println("ok");
                    continue;
                }
                if(text.equals("list")) {
                    catalog.list();
                    continue;
                }
                if(text.equals("play")) {
                    System.out.println("enter item name:");
                    String itemName = in.readLine();
                    catalog.play(itemName);
                    continue;
                }
                if(text.equals("save")) {
                    System.out.println("enter destination file path:");
                    String dest = in.readLine();
                    catalog.save(dest);
                    continue;
                }
                if(text.equals("load")) {
                    System.out.println("enter source file path:");
                    String src = in.readLine();
                    catalog.load(src);
                    continue;
                }
                System.out.println("Invalid command");
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (RuntimeException e){
                System.err.println(e);
            }
        }
    }

    public CatalogItem getItem(String name) throws RuntimeException{
        for(var i : this.items){
            if(name.equals(i.getName()))
                return i;
        }
        throw new InvalidData("no such item in catalog");
    }
}
