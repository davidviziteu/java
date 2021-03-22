package optional;

import compulsory.Picture;
import compulsory.Song;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainOptional {
    public static void main(String[] args) {
        var desktop = Desktop.getDesktop();
        var catalog = new CatalogOptional(desktop);
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
                    var addObj = new AddCommand(catalog, newSong);
                    addObj.execute();
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
                    var addObj = new AddCommand(catalog, newPic);
                    addObj.execute();
                    System.out.println("ok");
                    continue;
                }
                if(text.equals("list")) {
                    var listObj = new ListCommand(catalog);
                    listObj.execute();
                    continue;
                }
                if(text.equals("play")) {
                    System.out.println("enter item name:");
                    String itemName = in.readLine();
                    var playObj = new PlayCommand(catalog, itemName);
                    playObj.execute();
                    continue;
                }
                if(text.equals("save")) {
                    System.out.println("enter destination file path:");
                    String dest = in.readLine();
                    var saveObj = new SaveCommand(catalog, dest);
                    saveObj.execute();
                    continue;
                }
                if(text.equals("load")) {
                    System.out.println("enter source file path:");
                    String src = in.readLine();
                    var loadObj = new LoadCommand(catalog, src);
                    loadObj.execute();
                    continue;
                }
                System.out.println("Invalid command");
                System.out.println("Commands: play list save load add");
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (RuntimeException e){
                System.err.println(e);
            }
        }
    }
}
