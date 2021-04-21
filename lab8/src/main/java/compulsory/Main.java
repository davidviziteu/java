package compulsory;


import Model.Actor;
import Model.Genre;
import Model.Movie;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    static public void main(String[] args) throws SQLException {
        var db = DataBase.getInstance();
        var movie1 = new Movie(0, "interstellar", new Date(System.currentTimeMillis()), 100, 10);
        var movie2 = new Movie(1, "nu", new Date(System.currentTimeMillis()), 100, 10);
        var movie3 = new Movie(2, "stiu", new Date(System.currentTimeMillis()), 100, 10);
        var movie4 = new Movie(3, "alte", new Date(System.currentTimeMillis()), 100, 10);
        var movie5 = new Movie(4, "filme", new Date(System.currentTimeMillis()), 100, 10);

        DAO.insertMovie(db, movie1);
        DAO.insertMovie(db, movie2);
        DAO.insertMovie(db, movie3);
        DAO.insertMovie(db, movie4);
        DAO.insertMovie(db, movie5);
        var testMovie1 = DAO.getMovie(db, 0);
        var testMovie2 = DAO.getMovie(db, "nu");
        System.out.println(testMovie1);
        System.out.println(testMovie2);

        var genre1 = new Genre(1, "Drama");
        var genre2 = new Genre(2, "Action");
        var genre3 = new Genre(0, "Thriller");
        DAO.insertGenre(db, genre1);
        DAO.insertGenre(db, genre2);
        DAO.insertGenre(db, genre3);

        DAO.insertAssociation(db, 0, 0);
        DAO.insertAssociation(db, 1, 0);
        DAO.insertAssociation(db, 2, 0);
        DAO.insertAssociation(db, 3, 1);
        DAO.insertAssociation(db, 4, 2);

        //optional
        var actor1 = new Actor("Andrei","Popescu",18,9.7);
        var actor2 = new Actor("Bogdan","Ionescu",45,6.5);
        DAO.insertActor(db, actor1);
        DAO.insertActor(db, actor2);
//        DAO.insertActorProduction(db,0,1);
//        DAO.insertActorProduction(db,0,2);

    }

}
