package repositories;

import models.Movie;
import utils.MyManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieRepo {

    @PersistenceContext
    private final EntityManager em = MyManager.getInstance().initEntityManagerFactory().createEntityManager();

    public MovieRepo() {
    }

    public void create(Movie movie) {
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
    }

    public Movie findById(int id) {
        return em.find(Movie.class, id);
    }

    public List<Movie> findByName(String name) {
        TypedQuery query = em.createNamedQuery("Movie.findMovieByName", Movie.class)
                .setParameter("title", name);
        return query.getResultList();
    }
}
