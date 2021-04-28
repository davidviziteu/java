package optional;

import Model.Actor;
import Model.Director;
import Model.Genre;
import Model.Movie;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlGenerator {
    public static void toMarkUp(List<Movie> movies, List<Genre> genres, List<Actor> actors, List<Director> directors) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setDirectoryForTemplateLoading(new File("freemarker/"));
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            Template template = cfg.getTemplate("database.ftlh");

            Writer out = new OutputStreamWriter(new FileOutputStream("freemarker/database.html"));
            Map<String, List<Genre>> gen = new HashMap<>();
            Map<String, List<Movie>> mov = new HashMap<>();
            Map<String, List<Actor>> act = new HashMap<>();
            Map<String, List<Director>> dir = new HashMap<>();
            Map<String, List<Object>> all = new HashMap<>();
            gen.put("Genres", genres);
            mov.put("Movies", movies);
            act.put("Actors", actors);
            dir.put("Directors", directors);
            List<Object> allArr = new ArrayList<>();
            allArr.add(gen);
            allArr.add(mov);
            allArr.add(act);
            allArr.add(dir);
            all.put("Data", allArr);
            template.process(all, out);
        }
        catch (TemplateException te) {
            System.err.println("Couldn't generate html, template exception");
        }
        catch (IOException io) {
            System.err.println("Couldn't generate html, io exception");
            io.printStackTrace();
        }
    }
}
