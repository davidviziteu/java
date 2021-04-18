package compulsory;

import Model.Genre;
import Model.Movie;

import java.sql.*;

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
        Statement statement = db.createStatement();
        String createSql = "INSERT INTO GENRES VALUES (?, ?)";
        var createStmt = db.prepareStatement(createSql);
        createStmt.setInt(1, newObj.getId());
        createStmt.setString(2, newObj.getName());
        try {
            createStmt.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            if (DAO.getGenre(db, newObj.getId()).getName().equals(newObj.getName())) {
                System.err.println("Object is already in table.");
            } else {
                throw e;
            }
        }
    }

    public static void insertAssociation(Connection db, int id1, int id2) throws SQLException {
        Statement statement = db.createStatement();
        String createSql = "INSERT INTO ASSOCIATION VALUES (?, ?)";
        var createStmt = db.prepareStatement(createSql);
        createStmt.setInt(1, id1);
        createStmt.setInt(2, id2);
        try {
            createStmt.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
//            if (DAO.getGenre(db, newObj.getId()).getName().equals(newObj.getName())) {
//                System.err.println("Object is already in table.");
//            } else {
            throw e;
//            }
        }
    }


    public static Movie getMovie(Connection db, int id) throws SQLException {
        Statement statement = db.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM MOVIE WHERE ID = " + id);
        if(set.next())
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
        ResultSet set = statement.executeQuery("SELECT * FROM MOVIE WHERE TITLE LIKE '" + title + "'");
        if(set.next())
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
        if(set.next())
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
        Statement statement = db.createStatement();
        String createSql = "INSERT INTO movie VALUES (?, ?, ?, ?, ?)";
        var createStmt = db.prepareStatement(createSql);
        createStmt.setInt(1, newObj.getId());
        createStmt.setString(2, newObj.getTitle());
        createStmt.setDate(3, newObj.getReleaseDate());
        createStmt.setInt(4, newObj.getDuration());
        createStmt.setInt(5, newObj.getScore());
        try {
            createStmt.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            if (DAO.getMovie(db, newObj.getId()).getTitle().equals(newObj.getTitle())) {
                System.err.println("Object is already in table.");
            } else {
                throw e;
            }
        }
    }

}
