package optional;

import compulsory.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class CatalogOptional {

        public ArrayList<CatalogItem> items = new ArrayList<>();
        public Desktop desktopInstance;


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



        public CatalogOptional(Desktop desktopInstance){
            this.desktopInstance = desktopInstance;
        }

        public CatalogItem getItem(String name) throws RuntimeException{
            for(var i : this.items){
                if(name.equals(i.getName()))
                    return i;
            }
            throw new InvalidData("no such item in catalog");
        }
    }

