package compulsory;

import Model.Actor;
import Model.Director;
import Model.Genre;
import Model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.sqlite.SQLiteErrorCode;
import org.sqlite.SQLiteException;

public class DAO {
    public static Genre getGenre(Connection db, int id) throws SQLException {
        Statement statement = db.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM GENRES WHERE ID = " + id);
        if (set.next())
            return new Genre(
                    set.getInt("id"),
                    set.getString("name")
            );
        return new Genre(-1, "");
    }

    public static Genre getGenre(Connection db, String name) throws SQLException {
        Statement statement = db.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM GENRES WHERE NAME LIKE " + name);
        set.next();
        if (set.next())
            return new Genre(
                    set.getInt("id"),
                    set.getString("name")
            );
        return new Genre(-1, "");
    }

    public static Genre interrogateForGenre(Connection db, String query) throws SQLException {
        Statement statement = db.createStatement();
        ResultSet set = statement.executeQuery(query);
        if (set.next())
            return new Genre(
                    set.getInt("id"),
                    set.getString("name")
            );
        return new Genre(-1, "");
    }

    public static void insertGenre(Connection db, Genre newObj) throws SQLException {
        String createSql = "INSERT INTO GENRES  (name) VALUES (?)";
        var createStmt = db.prepareStatement(createSql);
        createStmt.setString(1, newObj.getName());
        try {
            createStmt.execute();
        } catch (SQLiteException e) {
            if (e.getResultCode() == SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE) {
                System.err.println("Genre named " + newObj.getName() + " is already in table.");
            } else {
                throw e;
            }
        }
    }

    public static void insertAssociation(Connection db, int id1, int id2) throws SQLException {
        String createSql = "INSERT INTO ASSOCIATION VALUES (?, ?)";
        var createStmt = db.prepareStatement(createSql);
        createStmt.setInt(1, id1);
        createStmt.setInt(2, id2);
        try {
            createStmt.execute();
        } catch (SQLiteException e) {
            if (e.getResultCode() == SQLiteErrorCode.SQLITE_CONSTRAINT_PRIMARYKEY)
                System.err.println("Association " + id1 + " <-> " + id2 + " already in the table");
            else throw e;
        }
    }


    public static Movie getMovie(Connection db, int id) throws SQLException {
        Statement statement = db.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM MOVIES WHERE ID = " + id);
        if (set.next())
            return new Movie(
                    set.getInt("id"),
                    set.getString("title"),
                    set.getDate("release_date"),
                    set.getInt("duration"),
                    set.getInt("score")
            );
        return new Movie(
                -1,
                "",
                null,
                0,
                0
        );
    }

    public static Movie getMovie(Connection db, String title) throws SQLException {
        Statement statement = db.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM MOVIES WHERE TITLE LIKE '" + title + "'");
        if (set.next())
            return new Movie(
                    set.getInt("id"),
                    set.getString("title"),
                    set.getDate("release_date"),
                    set.getInt("duration"),
                    set.getInt("score")
            );
        return new Movie(
                -1,
                "",
                null,
                0,
                0
        );
    }

    public static Movie interrogateForMovie(Connection db, String query) throws SQLException {
        Statement statement = db.createStatement();
        ResultSet set = statement.executeQuery(query);
        if (set.next())
            return new Movie(
                    set.getInt("id"),
                    set.getString("title"),
                    set.getDate("release_date"),
                    set.getInt("duration"),
                    set.getInt("score")
            );
        return new Movie(
                -1,
                "",
                null,
                0,
                0
        );
    }

    public static void insertMovie(Connection db, Movie newObj) throws SQLException {
        String createSql = "INSERT INTO movies (title, release_date, duration, score) VALUES (?, ?, ?, ?)";
        var createStmt = db.prepareStatement(createSql);
        createStmt.setString(1, newObj.getTitle());
        createStmt.setDate(2, newObj.getReleaseDate());
        createStmt.setInt(3, newObj.getDuration());
        createStmt.setDouble(4, newObj.getScore());
        try {
            createStmt.execute();
        } catch (SQLiteException e) {
            if (e.getResultCode() == SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE)
                System.err.println("Movie named " + newObj.getTitle() + " is already in table.");
            else throw e;
        }
    }


    public static void insertActor(Connection db, Actor newObj) throws SQLException {
        String createSql = "INSERT INTO actors (name,surname, age, popularity) VALUES (?, ?, ?, ?)";
        var createStmt = db.prepareStatement(createSql);
        createStmt.setString(1, newObj.getFamilyName());
        createStmt.setString(2, newObj.getSurname());
        createStmt.setInt(3, newObj.getAge());
        createStmt.setDouble(4, newObj.getPopularity());
        try {
            createStmt.execute();
        } catch (SQLiteException e) {
            if (e.getResultCode() == SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE)
                System.err.println("Actor named " + newObj.getFamilyName() + " is already in table.");
            else throw e;
        }
    }

    public static void insertDirector(Connection db, Director newObj) throws SQLException {
        String createSql = "INSERT INTO directors (name,surname, age, rating) VALUES (?, ?, ?, ?)";
        var createStmt = db.prepareStatement(createSql);
        createStmt.setString(1, newObj.getFamilyName());
        createStmt.setString(2, newObj.getSurname());
        createStmt.setInt(3, newObj.getAge());
        createStmt.setDouble(4, newObj.getRating());
        try {
            createStmt.execute();
        } catch (SQLiteException e) {
            if (e.getResultCode() == SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE)
                System.err.println("Director named " + newObj.getFamilyName() + " is already in table.");
            else throw e;
        }
    }

    public static void insertActorProduction(Connection db, int id_movie, int id_actor) {
        String createSql = "INSERT INTO actorProduction VALUES (?, ?)";
        try {
            var createStmt = db.prepareStatement(createSql);
            createStmt.setInt(1, id_movie);
            createStmt.setInt(2, id_actor);
            createStmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<Director> getAllDirectors(Connection db) {
        ArrayList<Director> finalResult = new ArrayList<>();
        try {
            Statement statement = db.createStatement();
            ResultSet set = null;
            set = statement.executeQuery("SELECT * FROM DIRECTORS");
            while (set.next()) {
                finalResult.add(
                        new Director(
                                set.getString(1),
                                set.getString(2),
                                set.getInt(3),
                                set.getDouble(4)
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return finalResult;
    }

    public static ArrayList<Movie> getAllMovies(Connection db) {
        ArrayList<Movie> finalResult = new ArrayList<>();
        try {
            Statement statement = db.createStatement();
            ResultSet set = null;
            set = statement.executeQuery("SELECT * FROM MOVIES");
            while (set.next()) {
                finalResult.add(
                        new Movie(
                                set.getInt(1),
                                set.getString(2),
                                set.getDate(3),
                                set.getInt(4),
                                (int) set.getDouble(5)
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return finalResult;
    }

    public static ArrayList<Genre> getAllGeneres(Connection db) {
        ArrayList<Genre> finalResult = new ArrayList<>();
        try {
            Statement statement = db.createStatement();
            ResultSet set = null;
            set = statement.executeQuery("SELECT * FROM genres");
            while (set.next()) {
                finalResult.add(
                        new Genre(
                                set.getInt(1),
                                set.getString(2)
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return finalResult;
    }

    public static ArrayList<Actor> getAllActors(Connection db) {
        ArrayList<Actor> finalResult = new ArrayList<>();
        try {
            Statement statement = db.createStatement();
            ResultSet set = null;
            set = statement.executeQuery("SELECT * FROM actors");
            while (set.next()) {
                finalResult.add(
                        new Actor(
                                set.getString(2),
                                set.getString(3),
                                set.getInt(4),
                                (int) set.getDouble(5)
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return finalResult;
    }
}
