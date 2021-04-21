package compulsory;

import entities.MoviesEntity;

import java.sql.Date;

public class test {
    public static void main(String[] args) {
        var movie = new MoviesEntity();
        movie.setId((short) 1);
        movie.setTitle("Titanic");
        movie.setReleaseDate(new Date(System.currentTimeMillis()));
        movie.setDuration(120);
        movie.setScore(4.5);

        var em = EntityManagerSingleton.getInstance().createEntityManager();
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
        em.close();
//        managerFactory.close();
    }
}
