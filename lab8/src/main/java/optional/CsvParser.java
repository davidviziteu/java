package optional;

import Model.Genre;
import Model.Movie;
import compulsory.DAO;
import compulsory.DataBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CsvParser {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println();

        String path = System.getProperty("user.dir") + "\\movies.csv";
        Connection connection = DataBase.getInstance();
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();
        int stop, k = 0;
        stop = 10;
        while ((line = br.readLine()) != null) {
            ++k;
            if(k == stop)
                break;
            try {
                String[] values = line.split("(?!\\B\"[^\"]*),(?![^\"]*\"\\B)");
                var movie = new Movie();
                movie.setTitle(values[1]);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date sqlDate = new Date(df.parse(values[4]).getTime());
                movie.setReleaseDate(sqlDate);

                movie.setDuration(Integer.parseInt(values[6]));
                movie.setScore(Double.parseDouble(values[14]));
                DAO.insertMovie(connection, movie);

                if (values[5].indexOf(',') != -1) {
                    values[5] = values[5].substring(1, values[5].length() - 1);
                    String[] genres = values[5].split(", ");
                    for (String s : genres) {
                        DAO.insertGenre(connection, new Genre(s));
                    }
                } else {
                    DAO.insertGenre(connection, new Genre(values[5]));
                    DAO.insertGenre(connection, new Genre(values[5]));
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | ParseException e) {
                System.out.println("Eroare la csv");
            }
        }
    }
}
