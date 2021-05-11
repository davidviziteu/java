package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class MyManager {

    private static final MyManager MANAGER = new MyManager();

    @PersistenceUnit(name = "default")
    protected EntityManagerFactory entityManagerFactory;


    private MyManager() {
    }

    public static MyManager getInstance() {
        return MANAGER;
    }

    public EntityManagerFactory initEntityManagerFactory() {
        if (entityManagerFactory == null)
            this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
        return entityManagerFactory;
    }

    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }

}