package repositories;

import models.Director;
import utils.MyManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class DirectorRepo {

    @PersistenceContext
    private EntityManager em = MyManager.getInstance().initEntityManagerFactory().createEntityManager();

    public DirectorRepo(){}

    public void create(Director director) {
        em.getTransaction().begin();
        em.persist(director);
        em.getTransaction().commit();
    }

    public Director findById(int id){
        return em.find(Director.class, id);
    }

    public List<Director> findByName(String name){
        TypedQuery query = em.createNamedQuery("Director.findDirectorByName", Director.class)
                .setParameter("name", name);
        return query.getResultList();
    }
}
