package compulsory;


public class DataBaseSingleton {
    static Connection db = null;

    static public DataBaseSingleton getInstance(){
        if(db == null)
            db = new Connection();
        return db;
    }

}
