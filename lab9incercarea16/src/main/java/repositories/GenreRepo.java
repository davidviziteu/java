package repositories;

import models.Genre;
import utils.MyManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenreRepo {


    @PersistenceContext
    private final EntityManager em = MyManager.getInstance().initEntityManagerFactory().createEntityManager();

    public GenreRepo(){}

    public void create(Genre genre) {
        em.getTransaction().begin();
        em.persist(genre);
        em.getTransaction().commit();
    }

    public Genre findById(int id){
        return em.find(Genre.class, id);
    }

    public List<Genre> findByName(String name){
        TypedQuery query = em.createNamedQuery("Genre.findGenreByName", Genre.class)
                .setParameter("name", name);
        return query.getResultList();
    }
}
