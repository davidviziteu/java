package compulsory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {
    private static EntityManagerFactory instance = null;
    public static EntityManagerFactory getInstance(){
        if(instance == null)
            instance = Persistence.createEntityManagerFactory("MyManagerFactory");
        return instance;
    }
}
